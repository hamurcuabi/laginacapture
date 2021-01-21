package com.emrhmrc.cameraxlib.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation
import ccom.emrhmrc.cameraxlib.fragments.BaseFragment
import com.bumptech.glide.Glide
import com.emrhmrc.cameraxlib.R
import com.emrhmrc.cameraxlib.adapter.PicturesAdapter
import com.emrhmrc.cameraxlib.databinding.FragmentPreviewBinding
import com.emrhmrc.cameraxlib.utils.*

class PreviewFragment : BaseFragment<FragmentPreviewBinding>(R.layout.fragment_preview) {
    private lateinit var picturesAdapter: PicturesAdapter
    private var currentPage = 0
    val returnIntent = Intent()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fragment = this // setting the variable for XML
        adjustInsets()
        val filePath = arguments?.getString("CameraXFilePath", "")
        returnIntent.putExtra("CameraXFilePath", filePath)
        binding.photo.let {
            Glide.with(it)
                    .load(filePath)
                    .into(it)
        }
    }

    /**
     * This methods adds all necessary margins to some views based on window insets and screen orientation
     * */
    private fun adjustInsets() {
        binding.layoutRoot.fitSystemWindows()
        binding.imageBack.onWindowInsets { view, windowInsets ->
            view.topMargin = windowInsets.systemWindowInsetTop
        }
        binding.imageShare.onWindowInsets { view, windowInsets ->
            view.bottomMargin = windowInsets.systemWindowInsetBottom
        }
    }

    fun shareImage() {
        if (!::picturesAdapter.isInitialized) return

        picturesAdapter.shareImage(currentPage) { share(it) }
    }

    fun deleteImage() {
        if (!::picturesAdapter.isInitialized) return

        picturesAdapter.deleteImage(currentPage) {
            if (outputDirectory.listFiles().isNullOrEmpty()) onBackPressed()
        }
    }

    fun done() {
        requireActivity().setResult(Activity.RESULT_OK, returnIntent)
        requireActivity().finish()
    }

    override fun onBackPressed() {
        view?.let { Navigation.findNavController(it).popBackStack() }
    }

}