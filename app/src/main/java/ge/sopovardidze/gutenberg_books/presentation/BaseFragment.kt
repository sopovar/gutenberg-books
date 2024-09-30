package ge.sopovardidze.gutenberg_books.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

typealias ViewBindingFactory<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

abstract class BaseFragment<ViewBindingType : ViewBinding> : Fragment() {

    abstract val viewBindingFactory: ViewBindingFactory<ViewBindingType>

    private var _binding: ViewBindingType? = null
    protected val binding: ViewBindingType
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = viewBindingFactory.invoke(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUi()
        observerViewModel()
    }

    protected abstract fun setUpUi()

    open fun observerViewModel() {

    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}