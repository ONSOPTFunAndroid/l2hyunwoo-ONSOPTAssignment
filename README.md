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
- ViewPager2

## 1주차 과제

### 종료 시점

2020/10/10 (토)

### 화면 캡쳐

| <img src="https://user-images.githubusercontent.com/54518925/96276106-8829eb80-100d-11eb-8b71-b6c5e0c6e1a5.png" width = "30%"/> | <img src="https://user-images.githubusercontent.com/54518925/96276117-8c560900-100d-11eb-9003-85cebc833385.png" width = "30%"/> |<img src="https://user-images.githubusercontent.com/54518925/96276120-8cee9f80-100d-11eb-9a3e-7a5f880ec386.png" width = "30%"/> |<img src="https://user-images.githubusercontent.com/54518925/96276123-8cee9f80-100d-11eb-94aa-1f57f5f9a083.png" width = "30%"/> | <img src="https://user-images.githubusercontent.com/54518925/96276125-8d873600-100d-11eb-89d4-58ade0a280de.png" width = "30%"/> | <img src="https://user-images.githubusercontent.com/54518925/96276127-8e1fcc80-100d-11eb-8da4-6887b66af16a.png" width = "30%"/> | <img src="hhttps://user-images.githubusercontent.com/54518925/96276129-8e1fcc80-100d-11eb-8f3e-8d2c2d1bc93a.png" width = "30%"/>

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

## 2주차 과제

### 종료 시점

2020/10/30 (금)

### 화면 캡쳐

<img src = "https://user-images.githubusercontent.com/54518925/97706159-8de8fc00-1af8-11eb-887c-cadfd0e99141.gif" width = 40%/>

### 주요 변경 사항

#### MainActivity/SignUpActivity의 역할 분리

- ViewModel과 View를 분리하여 데이터를 처리하는 클래스(ViewModel)/화면에서 일어나는 이벤트, 화면전환과 같은 일을 처리하는 클래스(Activity)로 분리하였다
- View 클래스에서 ViewModel의 데이터에 직접 참조하지 않고 ViewModel의 데이터가 변경되면 이벤트가 발생시키는 LiveData를 활용하였다.
  **MainActivity.kt**

```
override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Data Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.mainViewModel = activityViewModel
        binding.lifecycleOwner = this

        // Observe Data
        activityViewModel.isRegisterClicked.observe(this, {
            registerButtonClickListener(it)
        })

        activityViewModel.isSignIn.observe(this, {
            signInButtonClickListener(it)
        })
    }
```

### 과제 구현 코드

#### RecyclerView의 구성요소: ViewHolder, Adapter, Data Class

**data class ProjectData**

```
data class ProjectData (
    val img_portfolio : Int,
    val txt_title : String,
    val txt_explain : String
)
```

**class ProjectViewHolder**

```
class ProjectViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val img_portfolio = itemView.findViewById<ImageView>(R.id.img_project)
    val txt_title = itemView.findViewById<TextView>(R.id.txt_title)
    val txt_explain = itemView.findViewById<TextView>(R.id.txt_explain)

    fun onBindViewHolder(projectData: ProjectData) {
        img_portfolio.setImageResource(projectData.img_portfolio)
        txt_title.text = projectData.txt_title
        txt_explain.text = projectData.txt_explain
    }
}
```

**class ProjectAdapter**

```
class ProjectAdapter(private val context : Context) : RecyclerView.Adapter<ProjectViewHolder>() {

    var datas = mutableListOf<ProjectData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_project, parent, false)
        return ProjectViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) {
        holder.onBindViewHolder(datas[position])
    }

    override fun getItemCount(): Int = datas.size

}
```

#### ItemTouchHelper 클래스를 활용한 Drag, Swipe Delete

**ItemTouchHelperCallback**

```
class ItemTouchHelperCallback(private val context : Context,
                              adapter: ProjectAdapter) : ItemTouchHelper.Callback() {
    private val mAdapter = adapter

    // 길게 누를 시 아이템의 이동 가능 여부 결정
    override fun isLongPressDragEnabled(): Boolean {
        return true
    }
    // 아이템 스와이프 가능 여부 결정
    override fun isItemViewSwipeEnabled(): Boolean {
        return true
    }
    // 이동/스와이프 방향의 결정
    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        if(recyclerView.layoutManager == GridLayoutManager(context, 3)) {
            val dragFlags =
                ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
            val swipeFlags = 0
            return makeMovementFlags(dragFlags, swipeFlags)
        } else {
            val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
            val swipeFlags = ItemTouchHelper.START or ItemTouchHelper.END
            return makeMovementFlags(dragFlags, swipeFlags)
        }
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        if(viewHolder.itemViewType != target.itemViewType)
            return false

        mAdapter.onItemMove(viewHolder.adapterPosition, target.adapterPosition)
        return true
    }
    // 스와이프할 때 아이템 삭제 가능
    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        mAdapter.onItemDismiss(viewHolder.adapterPosition)
    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        if(actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            val alpha = ALPHA_FULL - Math.abs(dX) / viewHolder.itemView.width
            viewHolder.itemView.apply {
                setAlpha(alpha)
                translationX = dX
            }
        } else {
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
        }
    }

}
```

**class ProjectAdapter**
<br>아이템들이 이동한 것, 삭제된 결과를 Adapter 내의 List에 반영</br>

```
override fun onItemMove(from: Int, to: Int): Boolean {
        if(from < to) {
            for(i in from until to) {
                Collections.swap(datas, i, i+1)
            }
        } else {
            for(i in from downTo (to+1)) {
                Collections.swap(datas, i, i-1)
            }
        }
        notifyItemMoved(from, to)
        return true
    }

    override fun onItemDismiss(position: Int) {
        datas.removeAt(position)
        notifyItemRemoved(position)
    }
```

## 3주차 과제

### 종료 시점

2020/11/1 (일)

### 화면 캡쳐

<img src = "https://user-images.githubusercontent.com/54518925/98372750-98b80980-2081-11eb-9218-18a7b25adcc9.gif" width = 40%/>

### 주요 변경 사항

<img src = "https://user-images.githubusercontent.com/54518925/98372967-e6cd0d00-2081-11eb-85da-d335db532017.png" width = 40%/>

- Activity에서 Fragment로 뷰 변경
- Fragment로 뷰가 변함에 따라 ViewModel, DataBinding 방식도 변경

**SearchFragment.kt**

```
class SearchFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentSearchBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        return binding.root
    }
}
```

**ProjectFragment.kt**

```
class ProjectFragment : Fragment(), OnStartDragListener {
    private val welcomeViewModel : WelcomeViewModel by activityViewModels()
    private lateinit var projectBinding: FragmentProjectBinding
    private lateinit var mAdapter: ProjectAdapter
    private lateinit var touchHelper: ItemTouchHelper
    var data = mutableListOf<ProjectData>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        projectBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_project, container, false)
        projectBinding.welcomeViewModel = welcomeViewModel
        projectBinding.lifecycleOwner = viewLifecycleOwner
        return projectBinding.root
    }
}
```

- 지난번에 미완성한 2주차 성장과제 완료/3주차 필수과제 완료

### 주요 코드

**ViewPager2**(ViewPager 대신 ViewPager2로 구현)
- FragmentStateAdapter를 상속받는 MainViewPagerAdapter를 만들고 이를 ViewPager2에 부착

**MainViewPagerAdapter.kt**

```
class MainViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    // 전체 페이지 수
    override fun getItemCount(): Int = 3
    // 해당 위치에 맞는 Fragment 생성
    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> ProjectFragment()
            1 -> SearchFragment()
            else -> ProfileFragment()
        }
    }
}
```

**WelcomeActivity.kt**

```
class WelcomeActivity : AppCompatActivity() {
    private val welcomeViewModel: WelcomeViewModel by viewModels()
    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.welcomeViewModel = welcomeViewModel
        binding.lifecycleOwner = this

        val mainViewPagerAdapter = MainViewPagerAdapter(this)
        binding.vpFragmentSlider.apply {
            adapter = mainViewPagerAdapter
            registerOnPageChangeCallback(PageChangeCallBack())
        }
    }
}

```

**BottomNavigation**
- SetOnNavigationItemSelectedListener와 PagerChangeCallback을 활용하여 Bottom Nav의 탭을 누르면 ViewPager2의 페이지가 뜰 수 있게

```
class WelcomeActivity : AppCompatActivity() {
    private val welcomeViewModel: WelcomeViewModel by viewModels()
    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.welcomeViewModel = welcomeViewModel
        binding.lifecycleOwner = this

        val mainViewPagerAdapter = MainViewPagerAdapter(this)
        binding.vpFragmentSlider.apply {
            adapter = mainViewPagerAdapter
            registerOnPageChangeCallback(PageChangeCallBack())
        }
        binding.welcomeBottomnav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.main_project -> binding.vpFragmentSlider.currentItem = 0
                R.id.main_search -> binding.vpFragmentSlider.currentItem = 1
                R.id.main_proile -> binding.vpFragmentSlider.currentItem = 2
            }
            true
        }
    }
    // ViewPager2의 PagerChangeCallback(바텀 네비의 선택된 탭에 맞춰서 페이징함)
    private inner class PageChangeCallBack : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            binding.welcomeBottomnav.selectedItemId = when (position) {
                0 -> R.id.main_project
                1 -> R.id.main_search
                2 -> R.id.main_proile
                else -> throw IllegalAccessException("No Such Position")
            }
        }
    }
}
```

**Tab Layout**
- 검색 기능 구현을 위해 검색화면 구현, 아래 다른 검색 기능을 두기 위해 TabLayout 설치, addOnTabSelectedListener를 활용하여 ViewPager에 걸린 Fragment를 페이징

**SearchFragment.kt**

```
class SearchFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentSearchBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        val mainViewPagerAdapter = SearchViewPagerAdapter(childFragmentManager)
        binding.vpSearchSlider.adapter = mainViewPagerAdapter
        binding.tabSearch.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                binding.vpSearchSlider.setCurrentItem(tab?.position!!)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })
        return binding.root
    }
}
```
