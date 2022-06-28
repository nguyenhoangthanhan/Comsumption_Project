package gam.trainingcourse.rxjavademo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.lang.Exception
import java.util.*

class MainActivity : AppCompatActivity() {

    private var compositeDispossable:CompositeDisposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        initCompositeDisposable()
//
//        observableFromArray()
//        observableFromJust1()
//
//        observableCreate()

        testSingle()
    }

    private fun initCompositeDisposable(){
        if (compositeDispossable?.isDisposed == true){
            compositeDispossable = CompositeDisposable()
        }
    }

    override fun onDestroy() {
        clearCompositeDisposable()
        super.onDestroy()
    }

    private fun clearCompositeDisposable() {
        if (compositeDispossable?.isDisposed == false){
            compositeDispossable!!.dispose()
        }
    }

    private fun observableFromArray() {
        Observable.fromArray("One","Two","Three")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally {
                Log.w("Fresher","doFinally:  ${Thread.currentThread().name}")
            }
            .subscribe({item->
                Log.i("Fresher","onNext: $item - ${Thread.currentThread().name}")
            },{ex->
                Log.e("Fresher","onError: $ex -  ${Thread.currentThread().name}")
            },{
                Log.v("Fresher","onComplete:  ${Thread.currentThread().name}")
            })
    }

    private fun observableFromJust1() {
        val testArray = arrayOf("One", "Two")
        Observable.just(testArray)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally {
                Log.w("Fresher","doFinally:  ${Thread.currentThread().name}")
            }
            .subscribe({item->
                Log.i("Fresher","onNext: $item - ${Thread.currentThread().name}")
            },{ex->
                Log.e("Fresher","onError: $ex -  ${Thread.currentThread().name}")
            },{
                Log.v("Fresher","onComplete:  ${Thread.currentThread().name}")
            })
    }

    private fun observableCreate(){
        val createDisposable = Observable.create<Int> { emit ->
            try {
                for (i in 0 until 20) { emit.onNext(i) }
                Log.d("Fresher", "Initial_1: ${Thread.currentThread().name}")
                emit.onComplete()
            }catch (e:Exception){
                emit.onError(e)
            }
        }.flatMap {item->
            Observable.create<Int> {emit->
                if (item %2==0){ emit.onNext(item) }
                emit.onComplete()
                Log.d("Fresher", "Initial_2: ${Thread.currentThread().name}")
            }

        }
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally {
                Log.w("Fresher","doFinally:  ${Thread.currentThread().name}")
            }
            .subscribe({item->
                Log.i("Fresher","onNext: $item - ${Thread.currentThread().name}")
            },{ex->
                Log.e("Fresher","onError: $ex -  ${Thread.currentThread().name}")
            },{
                Log.v("Fresher","onComplete:  ${Thread.currentThread().name}")
            })

        compositeDispossable?.add(createDisposable)
    }

    //single
    private fun getNumbers():Single<List<Int>>{
        val numbers= arrayListOf<Int>(1,2,3,4,5,6,7,8,9)
        return Single.just(numbers)
    }

    private fun testSingle(){
        getNumbers().flatMap {numbers->
            val eventNumbers = arrayListOf<Int>()

            numbers.forEach{number->
                if (number%2==0){
                    eventNumbers.add(number)
                }
            }
            Single.just(eventNumbers)
        }
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally {
                Log.w("Fresher","doFinally:  ${Thread.currentThread().name}")
            }
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally {
                Log.w("Fresher","doFinally:  ${Thread.currentThread().name}")
            }
            .subscribe({numbers->
                Log.i("Fresher","onSuccess: $numbers- ${Thread.currentThread().name}")
            },{ex->
                Log.e("Fresher","onError: $ex- ${Thread.currentThread().name}")
            })
    }

    //Completable
    private fun printInformation(){

    }
}