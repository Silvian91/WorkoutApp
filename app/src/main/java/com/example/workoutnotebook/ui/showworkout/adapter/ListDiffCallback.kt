import androidx.recyclerview.widget.DiffUtil
import com.example.workoutnotebook.ui.showworkout.adapter.ShowWorkoutItemWrapper

class ListDiffCallback(
    private var oldList: List<ShowWorkoutItemWrapper>,
    private var newList: List<ShowWorkoutItemWrapper>
) : DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return (oldList[oldItemPosition].type.ordinal == newList[newItemPosition].type.ordinal)
    }

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}
