# app_base_view
快速添加app底部导航栏


快速使用方法：
xml中：
      
<base.common.BottomView.AppBottomView
        android:id="@+id/action_text"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:AppBottomView_Button_Text_Color="@color/color_text_app" />
        
activity中：
      setAppBottomViewOnClickListener().init().changeState()一次调用，请注意传入参数！！！！

gradle:

	Add it in your root build.gradle at the end of repositories:
	
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
	
	Step 2. Add the dependency
	
	dependencies {
	        implementation 'com.github.shrekcollege:app_base_view:Tag'
	}
	
	
			

