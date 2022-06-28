package gam.trainingcourse.rxjavademo

import android.util.Log
import io.reactivex.rxjava3.core.Observable

class Person {
    var name:String = ""

    fun createObservableDefer():Observable<String>{
        Log.d("Fresher", "createObservableDefer: $name - ${Thread.currentThread().name}")
        return Observable.defer{
            return@defer Observable.just(name)
        }
    }
}