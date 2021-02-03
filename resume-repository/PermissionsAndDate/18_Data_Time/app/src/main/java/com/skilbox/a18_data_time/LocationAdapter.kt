
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.skilbox.a18_data_time.LocatinViewHolder
import com.skilbox.a18_data_time.R
import com.skilbox.a18_data_time.UserLocation
import com.skilbox.a18_data_time.inflate
import kotlinx.android.synthetic.main.location.view.*

class LocationListViewAdapter(
    private val onItemClick: (position: Int) -> Unit
) : ListAdapter<UserLocation, LocatinViewHolder>(UserLocationDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocatinViewHolder {
        return LocatinViewHolder(parent.inflate(R.layout.location), onItemClick)
    }

    override fun onBindViewHolder(holder: LocatinViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class UserLocationDiffUtil : DiffUtil.ItemCallback<UserLocation>() {
    override fun areItemsTheSame(oldItem: UserLocation, newItem: UserLocation): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: UserLocation, newItem: UserLocation): Boolean {
        return oldItem == newItem
    }
}
