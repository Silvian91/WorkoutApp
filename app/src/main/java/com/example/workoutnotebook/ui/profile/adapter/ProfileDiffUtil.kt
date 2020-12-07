import androidx.recyclerview.widget.DiffUtil
import com.example.workoutnotebook.ui.profile.adapter.ProfileItemWrapper

class ProfileDiffUtil(
    private var oldList: List<ProfileItemWrapper>,
    private var newList: List<ProfileItemWrapper>
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