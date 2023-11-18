package com.example.guessinggame;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;

public class ResultFragmentDirections {
  private ResultFragmentDirections() {
  }

  @NonNull
  public static NavDirections resultFragment2ToActionGameFragment() {
    return new ActionOnlyNavDirections(R.id.resultFragment2_to_action_gameFragment);
  }
}
