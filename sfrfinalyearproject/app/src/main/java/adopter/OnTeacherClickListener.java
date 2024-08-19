package adopter;

import ModeClasees.Wish;
import ModeClasees.cuTeacher;
import ModeClasees.user;
import facultyClasses.mWishlist;

public interface OnTeacherClickListener {

    // Handle clicks on teacher items
    void onTeacherClick(user teacher);

    void onTeacherClick(cuTeacher teacher);

    void onTeacherClick(Wish wish);




    void onTeacherClick(mWishlist wish);
    void onTeacherClick(Object item);


}
