package com.udacity.shoestore.core.custom_views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.view.isVisible
import com.udacity.shoestore.databinding.ItemShoeBinding
import com.udacity.shoestore.shoe_list.model.Shoe

class ShoeView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
    var binding = ItemShoeBinding.inflate(LayoutInflater.from(context), this, true)

    fun setupView(shoe: Shoe) {
        binding.shoe = shoe
        binding.shoeName.isVisible = shoe.name.isNotEmpty()
        binding.shoeCompany.isVisible = shoe.company.isNotEmpty()
        binding.shoeSize.isVisible = shoe.size.isNotEmpty()
        binding.shoeDescription.isVisible = shoe.description.isNotEmpty()
    }
}
