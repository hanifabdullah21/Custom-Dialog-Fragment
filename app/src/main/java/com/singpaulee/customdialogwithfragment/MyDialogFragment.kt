package com.singpaulee.customdialogwithfragment


import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class MyDialogFragment : DialogFragment() {


    /**
     * The onCreateDialog is overridden to build your own custom Dialog container.
     * So, basically is provides a container to build a Dialog.
     *
     * */
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        //Check Arguments
        if (arguments != null) {
            //Check Argment key "notAlertDialog"
            if (arguments?.getBoolean("notAlertDialog")!!) {
                //Return default Dialog
                return super.onCreateDialog(savedInstanceState)
            }
        }

        //If arguments key "notAlertDialog" is null. Create an AlertDialog Builder
        val builder = AlertDialog.Builder(activity!!)
        builder.setTitle("Alert Dialog")
        builder.setMessage("Hello! I am Alert Dialog")
        builder.setPositiveButton("Cool", object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface, which: Int) {
                dismiss()
            }
        })
        builder.setNegativeButton("Cancel", object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface, which: Int) {
                dismiss()
            }
        })
        return builder.create()
    }

    /**
     * The onCreateView is used to supply the contents of the Dialog
     * and this is entirely responsible for drawing the Dialog.
     *
     * */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_dialog, container, false)
    }

    /**
     * The onViewCreated is called when the Dialog is created.
     * This is used to ensure that the view is created.
     *
     * */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val editText = view.findViewById<EditText>(R.id.inMobile)
        if (arguments != null && !TextUtils.isEmpty(arguments?.getString("mobile")))
            editText.setText(arguments?.getString("mobile"))
        val btnDone = view.findViewById<Button>(R.id.btnDone)
        btnDone.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                val dialogListener = activity as DialogListener
                dialogListener.onFinishEditDialog(editText.text.toString())
                dismiss()
            }
        })
    }

    override fun onResume() {
        super.onResume()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("Hey", "onCreate")
        var setFullScreen = false
        if (arguments != null) {
            setFullScreen = requireNotNull(arguments?.getBoolean("fullScreen"))
        }
        if (setFullScreen)
            setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Black_NoTitleBar_Fullscreen)
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    interface DialogListener {
        fun onFinishEditDialog(inputText: String)
    }
}
