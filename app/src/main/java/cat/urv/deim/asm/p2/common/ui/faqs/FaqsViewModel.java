package cat.urv.deim.asm.p2.common.ui.faqs;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FaqsViewModel extends ViewModel{
    private MutableLiveData<String> mText;

    public FaqsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("FAQs");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
