package com.nike.coding.dictionary.views.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.nike.coding.dictionary.R
import com.nike.coding.dictionary.core.BaseActivity
import com.nike.coding.dictionary.databinding.ActivityDictionaryListBinding
import com.nike.coding.dictionary.views.fragment.DictionaryDefinitionsListFragment
import dagger.android.AndroidInjection

class DictionaryDefinitionsListActivity : BaseActivity<ActivityDictionaryListBinding>() {

    override fun layoutRes() = R.layout.activity_dictionary_list

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        setSupportActionBar(dataBinding.toolBar)
        loadFragment(DictionaryDefinitionsListFragment.newInstance(), DictionaryDefinitionsListFragment.TAG, addToBackStack = false, isAnimate = false)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId) {
            R.id.home -> super.onBackPressed()
            R.id.menu_sort_by_thumbs_down -> return false
            R.id.menu_sort_by_thumbs_up -> return false
        }

        return true
    }
}