package cat.urv.deim.asm.p2.common.ui.articles;

        import androidx.lifecycle.LiveData;
        import androidx.lifecycle.MutableLiveData;
        import androidx.lifecycle.ViewModel;

public class ArticlesViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ArticlesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("ARTICLES");
    }

    public LiveData<String> getText() {
        return mText;
    }
}