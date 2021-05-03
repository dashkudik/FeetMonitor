package dashkudov.feetmonitor.presentation.fragments.home

import android.content.Intent
import android.net.Uri
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.feetmonitor.R
import dashkudov.feetmonitor.presentation.fragments.AbstractFragment
import dashkudov.feetmonitor.presentation.fragments.PagerImageFragment
import dashkudov.feetmonitor.presentation.fragments.adapters.ViewPagerAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

class HomeFragment : AbstractFragment<HomeViewModel>(R.layout.fragment_home) {

    @Inject
    lateinit var modelFactory: ViewModelProvider.Factory

    override val viewModel by lazy {
        createViewModel<HomeViewModel>()
    }

    override fun fragmentBlock() {
        root
            .getChildAt(0)
            .findViewById<ViewPager2>(R.id.viewPagerImages).adapter =
            ViewPagerAdapter(
                this,
                DISEASE_1_URLS
            ) { url, _ -> PagerImageFragment.newInstance(url) }

        root
            .getChildAt(1)
            .findViewById<ViewPager2>(R.id.viewPagerImages).adapter =
            ViewPagerAdapter(
                this,
                DISEASE_2_URLS
            ) { url, _ -> PagerImageFragment.newInstance(url) }

        root
            .getChildAt(0)
            .findViewById<TextView>(R.id.tvMoreInfo).setOnClickListener {
                startActivity(Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse("https://7010303.ru/zabolevanija/ploskostopie/")
                })
            }

        root
            .getChildAt(1)
            .findViewById<TextView>(R.id.tvMoreInfo).setOnClickListener {
                startActivity(Intent(Intent.ACTION_VIEW).apply {
                    data =
                        Uri.parse("https://kladzdor.ru/articles/poleznye-zametki-o-zdorove/Artrit_stopy%3A_simptomy_i_lechenie/")
                })
            }
    }

    override fun HomeViewModel.observeBlock() {

    }

    override fun HomeViewModel.processBlock() {

    }

    companion object {
        val DISEASE_1_URLS = mutableListOf(
            "https://static-sl.insales.ru/files/1/2669/9554541/original/%D0%A1%D1%82%D0%BE%D0%BF%D0%B01_e097922cff190e0ef4ce24a7a2195dff.png",
            "https://cs8.pikabu.ru/post_img/2017/03/10/7/1489146859144555341.jpg",
            "https://admin.cgon.ru/storage/upload/medialibrary/7a0d4a1fc8afe59f06e79f90df6fab51.png"
        )
        val DISEASE_2_URLS = mutableListOf(
            "https://www.power911.ru/media/k2/items/cache/621af29360685f88fae4c26f96ed9d8c_XL.jpg",
            "https://www.geromed.ru/upload/medialibrary/e28/e28ffb8bbd8b16d1901939a4fa9a8517.jpg",
        )
    }
}