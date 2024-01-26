import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.sellerapp.data.model.ProductData
import com.example.sellerapp.databinding.ItemTodayCustomerPaysBinding

class PageSecondAdapter : ListAdapter<ProductData, PageSecondAdapter.MyHolder>(TodayCustomerPaysDiffUtil){
    object TodayCustomerPaysDiffUtil : DiffUtil.ItemCallback<ProductData>() {
        override fun areItemsTheSame(oldItem: ProductData, newItem: ProductData): Boolean {
            return oldItem.monthPrice == newItem.monthPrice
        }

        override fun areContentsTheSame(oldItem: ProductData, newItem: ProductData): Boolean {
           return oldItem.monthPrice == newItem.monthPrice
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageSecondAdapter.MyHolder {
        return MyHolder(
            ItemTodayCustomerPaysBinding.inflate(
                LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun onBindViewHolder(holder: PageSecondAdapter.MyHolder, position: Int) {
        holder.bind()
    }

    inner class MyHolder(private var binding: ItemTodayCustomerPaysBinding): ViewHolder(binding.root){
        fun bind(){
            val pos = getItem(absoluteAdapterPosition)
            binding.amountPayable.text = pos.monthPrice.toString()
            binding.productName.text = pos.productName

        }
    }

}