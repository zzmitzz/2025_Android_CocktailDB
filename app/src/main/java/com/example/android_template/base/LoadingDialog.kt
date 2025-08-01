package com.example.android_template.base

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.android_template.R

/**
 * Loading dialog fragment for showing loading states
 */
class LoadingDialog : DialogFragment() {
    
    companion object {
        private const val ARG_MESSAGE = "message"
        

        fun create(message: String? = null): LoadingDialog {
            return LoadingDialog().apply {
                arguments = Bundle().apply {
                    putString(ARG_MESSAGE, message)
                }
            }
        }
    }
    
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return Dialog(requireContext(), R.style.LoadingDialog).apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(false)
            setCanceledOnTouchOutside(false)
        }
    }
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_loading, container, false)
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        val message = arguments?.getString(ARG_MESSAGE)
        val messageTextView = view.findViewById<TextView>(R.id.tv_loading_message)
        
        if (!message.isNullOrEmpty()) {
            messageTextView.text = message
            messageTextView.visibility = View.VISIBLE
        } else {
            messageTextView.visibility = View.GONE
        }
    }
} 