package br.com.mygym.exercise.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.lifecycle.Observer
import br.com.mygym.core.base.BaseActivity
import br.com.mygym.core.base.BaseFragment
import br.com.mygym.core.data.Result
import br.com.mygym.exercise.R
import br.com.mygym.exercise.databinding.ExerciseFragmentListBinding
import br.com.mygym.exercise.model.ExerciseItem
import br.com.mygym.exercise.view.adapter.ExerciseItemAdapter
import br.com.mygym.exercise.viewmodel.ExerciseListViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class ExerciseListFragment: BaseFragment() {

    private lateinit var binding: ExerciseFragmentListBinding
    private val viewModel: ExerciseListViewModel by viewModel()
    private val adapterExercise = ExerciseItemAdapter(::onClickDetail)
    private lateinit var itemMenuSearch: MenuItem

    private fun onClickDetail(exerciseItem: ExerciseItem) {

    }

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        val inflater = inflater
        inflater.inflate(R.menu.menu_search, menu)

        itemMenuSearch = menu.findItem(R.id.action_search)
        itemMenuSearch.isVisible = true
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_search -> {
                removeItemMenuSearch()
                showSearchClearebleEditText()
            }
        }
        return true
    }

    private fun showSearchClearebleEditText() {
        binding.appBar.searchClearableEditText.visibility = View.VISIBLE
    }

    private fun hideSearchClearebleEditText() {
        binding.appBar.searchClearableEditText.visibility = View.GONE
    }

    private fun showItemMenuSearch() {
        itemMenuSearch.isVisible = true
        hideSearchClearebleEditText()
        hideKeyboard()

    }

    private fun removeItemMenuSearch() {
        itemMenuSearch.isVisible = false
        showSearchClearebleEditText()
        hideKeyboard()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstance: Bundle?
    ): View? {
        binding = ExerciseFragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideKeyboard()
        setupToolbar()
        setupListeners()
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.exerciseListLiveData.observe(viewLifecycleOwner, Observer(this::onListExercise))
    }

    private fun onListExercise(result: Result<List<ExerciseItem>>) {
        when (result) {
            is Result.Loading -> {
                binding.contentLoading.visibility = View.VISIBLE
                binding.contentError.visibility = View.GONE
                binding.contentEmpty.visibility = View.GONE
                binding.exerciseList.visibility = View.GONE
            }
            is Result.Success -> {
                binding.contentLoading.visibility = View.GONE
                binding.contentError.visibility = View.GONE
                if(result.data.isNotEmpty()){
                    binding.contentEmpty.visibility = View.GONE
                    binding.exerciseList.visibility = View.VISIBLE
                    adapterExercise.setAllItems(result.data)
                } else{
                    binding.contentEmpty.visibility = View.VISIBLE
                    binding.exerciseList.visibility = View.GONE
                }

            }
            is Result.Failure -> {
                binding.contentLoading.visibility = View.GONE
                binding.contentEmpty.visibility = View.GONE
                binding.exerciseList.visibility = View.GONE
                binding.contentError.visibility = View.VISIBLE
                binding.contentError.setButtonOnClickListener {
                    binding.appBar.searchClearableEditText.clearText()
                }
            }
        }
    }

    private fun setupListeners() {
        binding.appBar.searchClearableEditText.initListeners(
            cancelButtonClickListener = ::cancelSearchButtonClick,
            textChangedListener = ::textChangedToSearch,
            clearButtonClickListener = ::clearSearchButtonClick
        )
    }

    private fun clearSearchButtonClick() {
        viewModel.loadExercises()
    }

    private fun setupToolbar() {
        (activity as BaseActivity).run {
            setSupportActionBar(binding.appBar.toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
            supportActionBar?.setHomeButtonEnabled(false)
        }
    }

    private fun textChangedToSearch(text: String) {
        viewModel.loadExercisesByFilter(text)
    }

    private fun cancelSearchButtonClick() {
        binding.appBar.searchClearableEditText.clearText()
        hideSearchClearebleEditText()
        showItemMenuSearch()
        hideKeyboard()
    }
}