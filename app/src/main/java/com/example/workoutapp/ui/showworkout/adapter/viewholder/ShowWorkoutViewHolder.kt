package com.example.workoutapp.ui.showworkout.adapter.viewholder

import android.view.View
import com.example.workoutapp.ui.common.BaseViewHolder
import com.example.workoutapp.ui.showworkout.adapter.ShowWorkoutItemWrapper.WorkoutTitle
import com.example.workoutapp.ui.showworkout.adapter.ShowWorkoutRecyclerAdapter
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.layout_workout_list.*

class ShowWorkoutViewHolder(
    override val containerView: View,
    private val listener: ShowWorkoutRecyclerAdapter.WorkoutViewHolderListener
) : BaseViewHolder<WorkoutTitle>(containerView), LayoutContainer {

    override fun bind(model: WorkoutTitle) {
        button_show_workout_name.text = model.workoutTitle.title
        show_workout_name_card.setOnClickListener {
            listener.onWorkoutClicked(model.workoutTitle.id!!)
        }
    }

    //show_workout_name_card.setCallback???
//        object : ViewDragHelper.Callback() {
//            override fun onViewReleased(releasedChild: View, xvel: Float, yvel: Float) {
//                super.onViewReleased(releasedChild, xvel, yvel)
//            }
//
//            override fun tryCaptureView(child: View, pointerId: Int): Boolean {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//            }
//        }


}