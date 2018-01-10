package it.arkadiuss.jaksiemasz_rxjava;


import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;


/**
 * Created by arkadius on 10.01.18.
 */

public class RxEditTextOnSubscibe implements ObservableOnSubscribe<String> {
    private EditText editText;

    RxEditTextOnSubscibe(EditText editText){
        this.editText=editText;
    }


    @Override
    public void subscribe(ObservableEmitter<String> e) throws Exception {
        this.editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                e.onNext(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
