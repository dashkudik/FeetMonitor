package dashkudov.feetmonitor.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.feetmonitor.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_image.*

class PagerImageFragment : Fragment() {
    private var image: String? = null

    companion object {
        @JvmStatic
        fun newInstance(image: String) =
            PagerImageFragment().apply {
                this.image = image
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_image, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Picasso.get().load(image!!).into(img)
    }
}