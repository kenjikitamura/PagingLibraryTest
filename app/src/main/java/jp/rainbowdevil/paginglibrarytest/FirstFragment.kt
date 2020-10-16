package jp.rainbowdevil.paginglibrarytest

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import jp.rainbowdevil.paginglibrarytest.repository.FirstViewModel
import org.koin.android.viewmodel.compat.ViewModelCompat

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    lateinit var adapter : TestItemAdapter

    private val viewModel : FirstViewModel by ViewModelCompat.viewModel(
        this,
        FirstViewModel::class.java
    )

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        adapter = TestItemAdapter(requireContext())
        val binding = jp.rainbowdevil.paginglibrarytest.databinding.FragmentFirstBinding.inflate(layoutInflater)
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter
        viewModel.list.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean = oldItem == newItem
        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean =
            oldItem.hashCode() == newItem.hashCode()
    }

    class TestItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView = itemView.findViewById<TextView>(R.id.text)
    }

    inner class TestItemAdapter(context: Context) : PagedListAdapter<String, TestItemViewHolder>(DIFF_CALLBACK) {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestItemViewHolder {
            val inflater = LayoutInflater.from(context)
            return TestItemViewHolder(inflater.inflate(R.layout.item, parent, false))
        }

        override fun onBindViewHolder(holder: TestItemViewHolder, position: Int) {
            holder.textView.text = getItem(position)
        }
    }

}




