# Welcome To ✨ON SOPT✨ Android
<img src="https://user-images.githubusercontent.com/54518925/97086165-ab334b80-165c-11eb-962f-0ef65a9fa034.png"/>
ON SOPT 안드로이드 파트 필수/성장 과제 Repository입니다.
저는 세미나 내용뿐만 아니라 제가 배워가는 내용을 기반으로 과제를 수행합니다.

## What Did You Use?
- Constraint/LinearLayout
- ViewModel
- LiveData
- Data Binding
- SharedPreference
- RecyclerView

## 1주차 과제
### 종료 시점
2020/10/10 (토)
### 화면 캡쳐
<img src="https://user-images.githubusercontent.com/54518925/96276106-8829eb80-100d-11eb-8b71-b6c5e0c6e1a5.png" width = "40%"/>
<img src="https://user-images.githubusercontent.com/54518925/96276117-8c560900-100d-11eb-9003-85cebc833385.png" width = "40%"/>
<img src="https://user-images.githubusercontent.com/54518925/96276120-8cee9f80-100d-11eb-9a3e-7a5f880ec386.png" width = "40%"/>
<img src="https://user-images.githubusercontent.com/54518925/96276123-8cee9f80-100d-11eb-94aa-1f57f5f9a083.png" width = "40%"/>
<img src="https://user-images.githubusercontent.com/54518925/96276125-8d873600-100d-11eb-89d4-58ade0a280de.png" width = "40%"/>
<img src="https://user-images.githubusercontent.com/54518925/96276127-8e1fcc80-100d-11eb-8da4-6887b66af16a.png" width = "40%"/>
<img src="hhttps://user-images.githubusercontent.com/54518925/96276129-8e1fcc80-100d-11eb-8f3e-8d2c2d1bc93a.png" width = "40%"/>

### 주요 코드
#### startActivityForResult <-> onActivityResult
메인 Activity에서 다른 Activity를 띄우고 그 Activity에서 입력한 값을 Activity를 종료하고 메인 액티비티로 돌아올 때 사용하고 싶으면 
- Main에서 startActvityForResult 호출
```
val intent = Intent(applicationContext, SignUpActivity::class.java)
startActivityForResult(intent,SIGN_UP_CODE)
```
- 다른 액티비티에서 intent에 필요한 데이터를 넣음
```
intent.putExtra("id", et_id.text.toString())
intent.putExtra("password", et_password.text.toString())
```
- requestCode로 정상종료가 되었는 지 판단하고 조건에 따라서 data 변수를 통해 intent에 저장했던 데이터 사용
```
if(requestCode == SIGN_UP_CODE) {
            loginId = data!!.getStringExtra("id")
            loginPassword = data.getStringExtra("password")
}
```

#### SharedPreference
- SharedPreference를 싱글턴 객체(object 객체)로 만듦 (LoginPreference)
- Application을 상속받은 클래스에서 LoginPreference 초기화
- 이미 싱글턴 객체에 필요한 변수에 대해서 getter/setter를 설정했으므로 싱글턴 객체의 멤버변수를 바꿔주는 것으로 SharedPreference 내의 value 변경 가능
```
    var myIsLogin: Boolean
            get() = prefs.getBoolean(isLogin.first, isLogin.second)
            set(value) = prefs.edit{
                it.putBoolean(isLogin.first, value)
            }

    var myId:String
        get() = prefs.getString(mail.first, mail.second)?:""
        set(value) = prefs.edit {
            it.putString(mail.first, value)
        }

    var myEtPassword:String
        get() = prefs.getString(password.first, password.second)?:""
        set(value) = prefs.edit() {
            it.putString(password.first, value)
        }
```
