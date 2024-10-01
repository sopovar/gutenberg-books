package ge.sopovardidze.gutenberg_books.presentation.utils

import android.view.LayoutInflater
import android.view.ViewGroup

typealias ViewBindingFactory<T> = (LayoutInflater, ViewGroup?, Boolean) -> T