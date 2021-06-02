package sg.edu.np.practical;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {
    DBHandler dbHandler = new DBHandler(this, null, null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        User user = (User) intent.getSerializableExtra("user");


        TextView nameView = findViewById(R.id.textView2);
        nameView.setText(user.getName());
        TextView descView = findViewById(R.id.textView);
        descView.setText(user.getDescription());

        Button followButton = findViewById(R.id.followButton);
        if (user.isFollowed()) {
            followButton.setText("unfollow");
        } else {
            followButton.setText("follow");
        }

        followButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setFollowed(!user.isFollowed());
                dbHandler.updateUser(user);
                updateFollowButton(user.isFollowed(), followButton);
            }
        });
    }

    private void updateFollowButton(boolean isFollowed, Button followButton) {
        String toast = "Followed";
        if (isFollowed == true) {
            followButton.setText("unfollow");
        } else {
            toast = "Unfollowed";
            followButton.setText("follow");
        }
        Toast.makeText(getApplicationContext(), toast, Toast.LENGTH_SHORT).show();
    }

}