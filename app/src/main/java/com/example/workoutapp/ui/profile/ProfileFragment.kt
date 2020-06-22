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
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import com.example.workoutapp.R
import com.example.workoutapp.R.string.*
import com.example.workoutapp.ui.WorkoutApplication
import com.example.workoutapp.ui.login.LoginActivity
import com.example.workoutapp.ui.signup.SignupActivity
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.LENGTH_SHORT
import com.jakewharton.rxbinding3.view.clicks
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import com.uber.autodispose.autoDispose
import kotlinx.android.synthetic.main.bottom_sheet_layout.*
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.toolbar.*
import javax.inject.Inject

class ProfileFragment : Fragment(), ProfileContract.View {

    @Inject
    lateinit var presenter: ProfileContract.Presenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        WorkoutApplication.get().components.createProfileComponent().inject(this)

        presenter.setView(this)
        presenter.start()

        setToolbar()
        bottomSheetDialogOnClickListener()

        button_log_out
            .clicks()
            .autoDispose(AndroidLifecycleScopeProvider.from(this, Lifecycle.Event.ON_DESTROY))
            .subscribe {
                presenter.logOutClicked()
            }
    }

    private fun setToolbar() {
        (activity as AppCompatActivity)
            .setSupportActionBar(toolbar)
        toolbar.title = "Workout App"
    }

    override fun showUsername(username: String) {
        profile_username.text = username
    }

    override fun showLogin() {
        startActivity(
            LoginActivity.newIntent(requireContext())
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        )
    }

    override fun showError() {
        Snackbar.make(
            profile_layout,
            text_error_registration_failed,
            LENGTH_SHORT
        ).show()
    }

    override fun showLoginActivity() {
        startActivity(
            SignupActivity.newIntent(requireContext()).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        )
    }

    override fun showLogOutConfirmationDialog() {
        //TODO: fix style issue
        AlertDialog.Builder(requireContext())
            .setMessage(text_alert_log_out)
            .setNegativeButton(
                text_dialog_alert_cancel
            ) { _, _ -> }
            .setPositiveButton(
                text_dialog_alert_confirm
            ) { _, _ -> presenter.onLogOutConfirmed() }
            .show()
    }

    private fun bottomSheetDialogOnClickListener() {
        button_add_profile_picture
            .clicks()
            .autoDispose(AndroidLifecycleScopeProvider.from(this, Lifecycle.Event.ON_DESTROY))
            .subscribe {
                val bottomSheetDialog = BottomSheetDialog(requireContext())
                val bottomSheetView = layoutInflater.inflate(
                    R.layout.bottom_sheet_layout,
                    null
                )
                galleryOnClickListener(bottomSheetView)
                cameraOnClickListener(bottomSheetView)
                bottomSheetDialog.setContentView(bottomSheetView)
                bottomSheetDialog.show()
            }
    }

    private fun galleryOnClickListener(bottomSheetView: View) {
        val gallery = bottomSheetView.findViewById<ImageView>(R.id.profile_open_gallery)
        gallery
            .clicks()
            .autoDispose(AndroidLifecycleScopeProvider.from(this, Lifecycle.Event.ON_DESTROY))
            .subscribe {
                presenter.onGalleryClicked()
            }
    }


    private fun cameraOnClickListener(bottomSheetView: View) {
        val camera = bottomSheetView.findViewById<ImageView>(R.id.profile_open_camera)
        camera
            .clicks()
            .autoDispose(AndroidLifecycleScopeProvider.from(this, Lifecycle.Event.ON_DESTROY))
            .subscribe {
            presenter.onCameraClicked()
        }
    }

    override fun openCamera() {
        val callCameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val packageManager = activity!!.packageManager
        if (callCameraIntent.resolveActivity(packageManager) != null) {
            startActivityForResult(callCameraIntent, CAMERA_REQUEST_CODE)
        }
    }

    private fun pickImageFromGallery() {
        presenter.onImageSelected()
    }

    override fun startIntent() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(
            intent,
            IMAGE_PICK_CODE
        ) // GIVE AN INTEGER VALUE FOR IMAGE_PICK_CODE LIKE 1000
    }

    override fun checkPermissionForImage() {
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
                ) // GIVE AN INTEGER VALUE FOR PERMISSION_CODE_READ LIKE 1001
                requestPermissions(
                    permissionCoarse,
                    1002
                ) // GIVE AN INTEGER VALUE FOR PERMISSION_CODE_WRITE LIKE 1002
            } else {
                pickImageFromGallery()
            }
        }
    }

    companion object {
        //image pick code
        private const val IMAGE_PICK_CODE = 1000

        //Permission code
        private const val PERMISSION_CODE = 1001

        //camera request mode
        private const val CAMERA_REQUEST_CODE = 0
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
                    //permission from popup granted
                    pickImageFromGallery()
                } else {
                    //permission from popup denied
                    Toast.makeText(requireContext(), "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    //handle result of picked image
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            CAMERA_REQUEST_CODE -> {
                if (resultCode == Activity.RESULT_OK && data != null) {
                    profile_image.setImageBitmap(data.extras.get("data") as Bitmap)
                }
            }
            PERMISSION_CODE -> {
                if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE) {
                    profile_image.setImageBitmap(data!!.extras.get("data") as Bitmap)
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
        button_log_out.setOnClickListener(null)
        button_add_profile_picture.setOnClickListener(null)

        super.onDestroyView()
    }

}