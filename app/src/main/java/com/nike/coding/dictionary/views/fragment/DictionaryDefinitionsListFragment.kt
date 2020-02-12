package com.nike.coding.dictionary.views.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.nike.coding.dictionary.R
import com.nike.coding.dictionary.core.BaseFragment
import com.nike.coding.dictionary.databinding.FragmentDictionaryListBinding
import com.nike.coding.dictionary.network.Status
import com.nike.coding.dictionary.network.model.Definition
import com.nike.coding.dictionary.viewmodel.DictionaryDefinitionViewModel
import com.nike.coding.dictionary.views.adapter.DictionaryDefinitionsListAdapter

class DictionaryDefinitionsListFragment : BaseFragment<DictionaryDefinitionViewModel, FragmentDictionaryListBinding>() {

    private var dictionaryDefinitionsListAdapter: DictionaryDefinitionsListAdapter? = null
    private var definitions: MutableList<Definition> = ArrayList()

    companion object {
        val TAG = DictionaryDefinitionsListFragment::class.java.simpleName

        fun newInstance(): DictionaryDefinitionsListFragment {
            val args = Bundle()
            val gitHubRepoListFragment = DictionaryDefinitionsListFragment()
            gitHubRepoListFragment.arguments = args
            return gitHubRepoListFragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun getViewModel(): Class<DictionaryDefinitionViewModel> = DictionaryDefinitionViewModel::class.java

    override fun layoutRes() = R.layout.fragment_dictionary_list

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataBinding.viewModel = viewModel
        dataBinding.lifecycleOwner = this

        viewModel.getDefinitions().observe(viewLifecycleOwner, Observer { resource ->
            if (resource.status == Status.SUCCESS && resource?.data != null && resource.data.isNotEmpty()) {
                definitions.clear()
                definitions.addAll(resource.data)
                dictionaryDefinitionsListAdapter?.setData(definitions)
            }
        })

        dictionaryDefinitionsListAdapter = DictionaryDefinitionsListAdapter()
        dataBinding.articleRecyclerView.layoutManager = LinearLayoutManager(activity)
        dataBinding.articleRecyclerView.adapter = dictionaryDefinitionsListAdapter

        dataBinding.searchEditText.setOnTouchListener(fun(view: View, event: MotionEvent): Boolean {
            if (event.action == MotionEvent.ACTION_UP) {
                if (event.rawX >= (dataBinding.searchEditText.right - dataBinding.searchEditText.compoundDrawables[2].bounds.width())) {
                    hideKeyboard(view)
                    viewModel.getDefinition(dataBinding.searchEditText.text.toString())
                    return true
                }
            }
            return false
        })

//        dataBinding.searchEditText.set { v ->
//            hideKeyboard(v)
//            viewModel.getDefinition(dataBinding.searchEditText.text.toString())
//        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.home -> return false
            R.id.menu_sort_by_thumbs_down -> {
                definitions.sortByDescending { definition -> definition.thumbs_down }
                dictionaryDefinitionsListAdapter?.setData(definitions)
                return true
            }
            R.id.menu_sort_by_thumbs_up -> {
                definitions.sortByDescending { definition -> definition.thumbs_up }
                dictionaryDefinitionsListAdapter?.setData(definitions)
                return true
            }
        }

        return true
    }
}