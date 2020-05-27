package cat.urv.deim.asm.p2.common.ui.favorites;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FavoritesViewModel extends ViewModel{
    private MutableLiveData<String> mText;

    public FavoritesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("FAVORITES");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
