package com.frsarker.todotask;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Welcome extends AppCompatActivity {
    View _bg__let_s_start_ek2;
    private ImageView rectangle_1;
    private TextView task_management___to_do_list;
    private TextView let_s_start_ek3;
    private TextView this_productive_tool_is_designed_to_help_you_better_manage_your_task_project_wise_conveniently_;
    private ImageView female_sitting_on_the_floor_with_cup_in_hand_and_laptop_on_leg;
    private ImageView vase_with_tulips__glasses_and_pencil;
    private ImageView blue_stopwatch_with_pink_arrow;
    private ImageView multicolored_smartphone_notifications;
    private ImageView pie_chart;
    private ImageView close_up_of_pink_coffee_cup;
    private  ImageView blue_desk_calendar;
    private ImageView arrow___left_ek1;
    Button let_start;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        task_management___to_do_list = findViewById(R.id.task_management___to_do_list);
        this_productive_tool_is_designed_to_help_you_better_manage_your_task_project_wise_conveniently_ = findViewById(R.id.this_productive_tool_is_designed_to_help_you_better_manage_your_task_project_wise_conveniently_);
        female_sitting_on_the_floor_with_cup_in_hand_and_laptop_on_leg = findViewById(R.id.female_sitting_on_the_floor_with_cup_in_hand_and_laptop_on_leg);
        vase_with_tulips__glasses_and_pencil = findViewById(R.id.vase_with_tulips__glasses_and_pencil);
        blue_stopwatch_with_pink_arrow = findViewById(R.id.blue_stopwatch_with_pink_arrow);
        multicolored_smartphone_notifications = findViewById(R.id.multicolored_smartphone_notifications);
        pie_chart = findViewById(R.id.pie_chart);
        close_up_of_pink_coffee_cup = findViewById(R.id.close_up_of_pink_coffee_cup);
        blue_desk_calendar = findViewById(R.id.blue_desk_calendar);
        let_start = findViewById(R.id.button_let_start);
        let_start.setOnClickListener(view->{
            startActivity(new Intent(Welcome.this,MainActivity.class));
        });
    }
}
