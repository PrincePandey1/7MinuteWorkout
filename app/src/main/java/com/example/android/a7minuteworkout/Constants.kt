package com.example.android.a7minuteworkout

class Constants {

    companion object{
        fun defaultExerciseList() {
            val exerciseList = ArrayList<ExerciseModel>()

            val jumpingJacks = ExerciseModel(1,
            "JumpingJacks",
            R.drawable.ic_jumping_jack,
            false,
            false)
            exerciseList.add(jumpingJacks)

            val wallSit = ExerciseModel(2,
                    "WallSit",
                    R.drawable.ic_wall_sit,
                    false,
                    false)
            exerciseList.add(wallSit)

            val pushUps = ExerciseModel(3,
                    "PushUps",
                    R.drawable.ic_push_ups,
                    false,
                    false)
            exerciseList.add(pushUps)

            val pullUps = ExerciseModel(4,
                    "pullUps",
                    R.drawable.ic_pull_Ups,
                    false,
                    false)
            exerciseList.add(pullUps)

            val plank = ExerciseModel(4,
                    "StepUps",
                    R.drawable.ic_plank,
                    false,
                    false)
            exerciseList.add(plank)
        }
    }
}