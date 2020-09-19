package com.ztc.dailogfragment

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.fragment_commont_dialog.*

/**
 * @CreateDate:     2020/9/19 2:44 PM
 * @Description:    描述
 */
class CommonDialog(var builder: Builder) : DialogFragment() {
    companion object {
        var TAG  = CommonDialog::class.java.simpleName

        fun newInstance(builder: Builder):CommonDialog{
            return CommonDialog(builder)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.e(TAG,"-- onCreate --")
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        Log.e(TAG,"-- onCreateView --")
        getDialog()!!.getWindow()!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));//透明背景
        val view  = inflater.inflate(R.layout.fragment_commont_dialog,container,false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.e(TAG,"-- onActivityCreated --")

        setMessage(builder.msg)
        setButton(builder.buttonTitle, builder.buttonBg)
        setLayoutBg(builder.resource)

        bt_close.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                builder.buttonListener?.apply {
                    onClick(v)
                }

                dismiss()
            }
        })
    }


    fun setMessage(msg: String) {
        if (!TextUtils.isEmpty(msg)) {
            tv_message.text = msg
        }
    }

    fun setButton(title: String, resId: Int) {
        if (!TextUtils.isEmpty(title)) {
            bt_close.text = title
        }

        if (resId != 0) {
            bt_close.setBackgroundResource(resId)
        }
    }

    fun setLayoutBg(resId: Int) {
        if (resId != 0) {
            ll_layout.setBackgroundResource(resId)
        }
    }


    class Builder(){

        var iconTip: Int = 0
        var msg: String = ""
        var buttonTitle: String = ""
        var buttonBg: Int = 0
        var resource: Int = 0
        var buttonListener: View.OnClickListener? = null

        fun setMessage(msg: String): Builder {
            this.msg = msg
            return this
        }

        fun setIcon(resId: Int): Builder {
            iconTip = resId
            return this
        }

        fun setBtTitle(title: String): Builder {
            buttonTitle = title
            return this
        }

        fun setBtBg(resId: Int): Builder {
            buttonBg = resId
            return this
        }

        fun setPositiveButton(onListenr: View.OnClickListener): Builder {
            buttonListener = onListenr
            return this
        }

        fun create(): CommonDialog {
            return CommonDialog.newInstance(this)

        }



    }





}