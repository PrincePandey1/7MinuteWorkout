package com.example.android.a7minuteworkout

class Constants {

    companion object {
        fun defaultExerciseList():ArrayList<ExerciseModel> {
            val exerciseList = ArrayList<ExerciseModel>()

            val jumpingJacks = ExerciseModel(1,
                    "Jumping Jack",
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
                    "PushUp",
                    R.drawable.ic_push_ups,
                    false,
                    false)
            exerciseList.add(pushUps)

            val pullUps = ExerciseModel(4,
                    "PullUp",
                    R.drawable.ic_pull_ups,
                    false,
                    false)
            exerciseList.add(pullUps)

            val plank = ExerciseModel(5,
                    "Plank",
                    R.drawable.ic_plank,
                   false ,
                false)
            exerciseList.add(plank)

            return exerciseList
        }

    }
}