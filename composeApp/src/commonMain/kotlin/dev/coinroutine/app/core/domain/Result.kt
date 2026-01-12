package dev.coinroutine.app.core.domain

sealed interface Result <out D, out E: Error>{

    data class Success<out D>(val data: D): Result<D, Nothing>
    data class Error<out E: dev.coinroutine.app.core.domain.Error>(val error: E): Result<Nothing, E>

}

//Fungsi ini digunakan untuk mengubah data di dalam Success tanpa mengubah status error-nya.
inline fun <T, E: Error, R> Result<T, E>.map(map: (T) -> R): Result<R, E>{
    return when(this){
       is Result.Error -> Result.Error(error)
       is Result.Success -> Result.Success(map(data))
    }
}

//Fungsi ini digunakan saat kita tidak peduli dengan isi datanya dan hanya ingin tahu apakah operasinya berhasil atau tidak.
// Ia mengubah Result<T, E> menjadi Result<Unit, E>.
fun <T, E: Error, R > Result<T, E>.asEmptyDataResult() : EmptyResult<E>{
    return map {  }
}

/*
Fungsi ini digunakan untuk menjalankan "aksi sampingan" (seperti menampilkan log, menyimpan ke database, atau navigasi)
jika hasilnya berhasil. Ia mengembalikan this (objek yang sama) sehingga kamu bisa melakukan chaining (pemanggilan beruntun).
*/
inline fun <T, E: Error> Result<T, E>.onSuccess(action: (T) -> Unit) : Result<T, E>{
    return when(this){
        is Result.Error -> this
        is Result.Success -> {
            action(data)
            this
        }
    }
}

//agar kode lebih mudah dibaca. EmptyResult<MyError> jauh lebih jelas dibaca daripada Result<Unit, MyError>.
typealias EmptyResult<E> = Result<Unit, E>