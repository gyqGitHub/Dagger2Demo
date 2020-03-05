package com.example.daggerdemo.auto.dagger.inject

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import com.example.daggerdemo.R
import kotlinx.android.synthetic.main.activity_login_inject.*
import javax.inject.Inject

/**
 * 通过 @Inject 注解字段进行依赖注入（协助 Comment 组件解决依赖哪里去的问题）
 *     1。首先通过 @Inject 注解字段（injectViewModel）告诉 dagger 需要注入的依赖，并自动生成一个 MembersInjector 类（
 *        如LoginActivity_MembersInjector，该类通过 injectLoginViewModel() 来注入LoginViewModel
 *
 *     2。其次还要通过在 comment 组件中的inject(activity:XxActivity)方法来告诉 Dagger @Inject注解的字段所在类(如LoginActivity)（依赖哪里去）
 *
 * 注意：1。在 Activity 中使用时，需要在 onCreate() 中的 super.onCreate(savedInstanceState) 调用之前
 *      2。在 Fragment 中使用时，需要在 onAttach() 中使用，在 super.onAttach() 调用之前之后都可以
 *
 *  问题？dagger2 是否可以注入静态和局部变量
 *
 * @author gyq
 * @date 2020-02-24
 */
class InjectActivity: Activity() {
    //⚠️注解的可访问性修饰符不能是private
    @Inject
    lateinit var injectViewModel: InjectViewModel

    @Inject
    lateinit var mUser:UserData

    //如果该Activity有添加Fragment，则可以通过该组件的inject(XxFragment)
    lateinit var loginComponent: InjectComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        //1。在 Activity 中使用时，需要在 onCreate() 中的 super.onCreate(savedInstanceState) 调用之前
        //   不调用这一句，loginViewModel是没有赋值的
        loginComponent = DaggerInjectComponent.create().apply {
            inject(this@InjectActivity)
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_inject)

        btn_test_inject.setOnClickListener {
            if(this::injectViewModel.isInitialized){
                Toast.makeText(this,"依赖注入成功",Toast.LENGTH_SHORT).show()
            }
        }

        btn_show_name.setOnClickListener {
            if(this::mUser.isInitialized){
                Toast.makeText(this,mUser.name,Toast.LENGTH_SHORT).show()
            }
        }
    }

}
