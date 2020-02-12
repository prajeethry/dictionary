package com.nike.coding.dictionary.views.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.nike.coding.dictionary.core.BaseAdapter
import com.nike.coding.dictionary.databinding.ItemDictionaryDefinitionBinding
import com.nike.coding.dictionary.network.model.Definition
import com.nike.coding.dictionary.views.adapter.DictionaryDefinitionsListAdapter.DictionaryDefinitionViewHolder

class DictionaryDefinitionsListAdapter : BaseAdapter<DictionaryDefinitionViewHolder, Definition>() {

    private val definitions: MutableList<Definition> = ArrayList()

    override fun setData(data: List<Definition>) {
        definitions.clear()
        definitions.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): DictionaryDefinitionViewHolder {
        return DictionaryDefinitionViewHolder.create(LayoutInflater.from(viewGroup.context), viewGroup)
    }

    override fun onBindViewHolder(viewHolder: DictionaryDefinitionViewHolder, i: Int) {
        viewHolder.onBind(definitions[i])
    }

    override fun getItemCount(): Int {
        return definitions.size
    }

    class DictionaryDefinitionViewHolder private constructor(private val binding: ItemDictionaryDefinitionBinding) : ViewHolder(binding.root) {

        fun onBind(definition: Definition) {
            binding.definition = definition
            binding.executePendingBindings()
        }

        companion object {
            fun create(inflater: LayoutInflater, parent: ViewGroup): DictionaryDefinitionViewHolder {
                val binding = ItemDictionaryDefinitionBinding.inflate(inflater, parent, false)
                return DictionaryDefinitionViewHolder(binding)
            }
        }
    }
}