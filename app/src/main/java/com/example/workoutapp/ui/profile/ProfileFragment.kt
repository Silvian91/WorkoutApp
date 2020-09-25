package com.example.workoutapp.ui.profile

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.workoutapp.R
import com.example.workoutapp.R.string.*
import com.example.workoutapp.domain.extension.doOnIoObserveOnMain
import com.example.workoutapp.ui.common.BaseFragment
import com.example.workoutapp.ui.error.ErrorType
import com.example.workoutapp.ui.login.LoginActivity
import com.example.workoutapp.ui.profile.adapter.ProfileAdapter
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.LENGTH_SHORT
import com.jakewharton.rxbinding3.view.clicks
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import com.uber.autodispose.autoDispose
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.bottom_sheet_layout.*
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : BaseFragment() {

    private lateinit var viewModel: ProfileViewModel

    private lateinit var profileAdapter: ProfileAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(
            this, viewModelFactory.get()
        ).get(ProfileViewModel::class.java)

        initProfileRecyclerView()
        setToolbar()
        subscribeToViewState()
    }

    private fun initProfileRecyclerView() {
        recycler_view_profile.apply {
            layoutManager = LinearLayoutManager(requireContext())
            profileAdapter =
                ProfileAdapter(
                    ProfileAdapter.ProfileListener {
                        viewModel.onBottomSheetClicked()
                    },
                    this@ProfileFragment.lifecycle
                )
            adapter = profileAdapter
        }
    }

    private fun subscribeToViewState() {
        viewModel.viewState
            .doOnIoObserveOnMain()
            .subscribeBy { state ->
                profileAdapter.setData(state.items)
                showLogin(state.login)
                openBottomSheetDialog(state.bottomSheetDialog)
                openCamera(state.cameraClicked)
                checkPermissionForImage(state.permissionCheck)
                openGallery(state.galleryPermission)
            }
            .addTo(compositeDisposable)
    }

    private fun handleError(error: ErrorType?) {
        error?.run {
            showError()
        } ?: run {
            //TODO: hideError()
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        AlertDialog.Builder(requireContext())
            .setMessage(text_alert_log_out)
            .setNegativeButton(
                text_dialog_alert_cancel
            ) { _, _ -> }
            .setPositiveButton(
                text_dialog_alert_confirm
            ) { _, _ -> viewModel.onLogOutConfirmed() }
            .show()
        return super.onOptionsItemSelected(item)
    }

    private fun setToolbar() {
        (activity as AppCompatActivity)
            .setSupportActionBar(toolbar)
    }

    private fun showLogin(isLoading: Boolean) {
        if (isLoading) {
            startActivity(LoginActivity.newIntent(requireContext()))
            activity!!.finish()
        }
    }

    private fun showError() {
        Snackbar.make(
            profile_layout,
            text_unknown_error,
            LENGTH_SHORT
        ).show()
    }

    private fun openBottomSheetDialog(bottomSheet: Boolean) {
        val bottomSheetDialog = BottomSheetDialog(requireContext())
        val bottomSheetView = layoutInflater.inflate(
            R.layout.bottom_sheet_layout,
            null
        )
        galleryOnClickListener(bottomSheetView)
        cameraOnClickListener(bottomSheetView)
        bottomSheetDialog.setContentView(bottomSheetView)
        if (bottomSheet) {
            bottomSheetDialog.show()
        } else {
            bottomSheetDialog.cancel()
        }
    }


    private fun galleryOnClickListener(bottomSheetView: View) {
        val gallery = bottomSheetView.findViewById<ImageView>(R.id.profile_open_gallery)
        gallery
            .clicks()
            .autoDispose(AndroidLifecycleScopeProvider.from(this, Lifecycle.Event.ON_DESTROY))
            .subscribe {
                viewModel.onGalleryClicked()
            }
    }

    private fun cameraOnClickListener(bottomSheetView: View) {
        val camera = bottomSheetView.findViewById<ImageView>(R.id.profile_open_camera)
        camera
            .clicks()
            .autoDispose(AndroidLifecycleScopeProvider.from(this, Lifecycle.Event.ON_DESTROY))
            .subscribe {
                viewModel.onCameraClicked()
            }
    }

    private fun openCamera(cameraClicked: Boolean) {
        if (cameraClicked) {
            Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
                takePictureIntent.resolveActivity(activity!!.packageManager)?.also {
                    startActivityForResult(takePictureIntent, CAMERA_REQUEST_CODE)
                }
            }
        }
    }

    private fun openGallery(galleryPermission: Boolean) {
        if (galleryPermission) {
            val galleryIntent = Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            )
            startActivityForResult(galleryIntent, GALLERY_REQUEST_CODE)
        }
    }

    private fun galleryOption() {
        viewModel.onGalleryOption()
    }

    private fun checkPermissionForImage(permissionCheck: Boolean) {
        if (permissionCheck) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if ((checkSelfPermission(
                        requireContext(),
                        Manifest.permission.READ_EXTERNAL_STORAGE
                    ) == PackageManager.PERMISSION_DENIED)
                    && (checkSelfPermission(
                        requireContext(),
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    ) == PackageManager.PERMISSION_DENIED)
                ) {
                    val permission = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                    val permissionCoarse = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)

                    requestPermissions(
                        permission,
                        PERMISSION_CODE
                    )
                    requestPermissions(
                        permissionCoarse,
                        1002
                    )
                } else {
                    galleryOption()
                }
            }
        }
    }

    companion object {
        private const val PERMISSION_CODE = 1001

        private const val CAMERA_REQUEST_CODE = 1

        private const val GALLERY_REQUEST_CODE = 1234
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            PERMISSION_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED
                ) {
                    galleryOption()
                } else {
                    Toast.makeText(requireContext(), "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            CAMERA_REQUEST_CODE -> {
                if (resultCode == Activity.RESULT_OK && data != null) {
                    val imageBitmap = data!!.extras.get("data") as Bitmap
                    viewModel.setImage(imageBitmap)
                }
            }
            GALLERY_REQUEST_CODE -> {
                if (resultCode == Activity.RESULT_OK && requestCode == GALLERY_REQUEST_CODE) {
                    data?.data?.let { uri ->
                        val bitmap =
                            MediaStore.Images.Media.getBitmap(requireContext().contentResolver, uri)
                        viewModel.setImage(bitmap)
                    }
                }
            }
            else -> {
                Snackbar.make(
                    bottom_layout, text_unknown_error,
                    LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onDestroyView() {
        compositeDisposable.clear()

        super.onDestroyView()
    }

}