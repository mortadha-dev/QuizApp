package tun.dev.comeon.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class QuizCategory {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo
    private String category;
}
