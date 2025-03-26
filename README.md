# 🌐 Android Global Lottie Loader Utility
A reusable, customizable Lottie-based loading overlay for Android apps to block UI during background tasks.

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![GitHub issues](https://img.shields.io/github/issues/ashrafulwpdev/GlobalLottieLoader)](https://github.com/ashrafulwpdev/GlobalLottieLoader/issues)
[![GitHub stars](https://img.shields.io/github/stars/ashrafulwpdev/GlobalLottieLoader)](https://github.com/ashrafulwpdev/GlobalLottieLoader/stargazers)
[![GitHub forks](https://img.shields.io/github/forks/ashrafulwpdev/GlobalLottieLoader)](https://github.com/ashrafulwpdev/GlobalLottieLoader/network/members)

✅ **Company:** [Softourtech](https://github.com/Softourtech)  
✅ **Developer:** [Ashraful Islam](https://github.com/ashrafulwpdev)  
✅ **License:** [MIT License](LICENSE)  
✅ **Free for personal & commercial use** - Just credit [Softourtech](https://github.com/Softourtech)

---

## 🚀 Features
- 🔄 **Reusable Global Loader**: Use it anywhere in your app to block UI during background tasks.
- 🎨 **Optional Rounded Box**: Add a rounded background to the Lottie animation for a polished look.
- 🌈 **Dynamic Color Customization**: Change the Lottie animation’s color dynamically using modern Lottie color filters.
- 🚫 **Blocks User Interaction**: Prevents user interaction while the loader is visible.
- ✨ **Lottie JSON Support**: Supports Lottie animations from `res/raw/` with a fallback mechanism.
- 🛠 **Error Handling**: Includes validation for animation resources and a fallback animation if the specified resource is missing.
- 🎥 **Smooth Animations**: Fade-in and fade-out transitions for the loader overlay.

---

## 📂 How to Add
1. **Copy** `BaseActivity.java` into your `com.softourtech.smartpharmacy.utils` folder.
2. **Extend** your activities from `BaseActivity`.
3. **Add Lottie JSON files** into `res/raw/` (e.g., `default_loader.json`, `loading_animation.json`, `upload_animation.json`).
4. **Add the Lottie dependency** to your `build.gradle`:
   ```gradle
   implementation 'com.airbnb.android:lottie:6.4.0'
   ```
5. **Add a rounded background drawable (optional, for useRoundedBox)**:
   Create a file `res/drawable/loader_background.xml` with the following content:
   ```xml
   <shape xmlns:android="http://schemas.android.com/apk/res/android">
       <solid android:color="#FFFFFF"/>
       <corners android:radius="16dp"/>
   </shape>
   ```

---


# Easy Loader Usage Examples in a Fragment

These examples show how to use the loader from the `BaseActivity` class inside a Fragment. Each one explains how to show the loader, what it does, and how to hide it. The loader is a spinning animation that covers your screen while something is loading.

---

## Hiding the Loader

### Hiding Loader
To hide the loader:
```java
hideLoader();
```

### Hiding with a Timer

```java
// Hide the loader after 3 seconds
new Handler().postDelayed(() -> {
    BaseActivity activity = (BaseActivity) getActivity();
    if (activity != null) activity.hideLoader();
}, 3000);
```

**What Happens:** The loader hides after a specified time delay.

**When to Use:** For tasks with a known duration.

---

### Hiding with a Button

```java
Button hideButton = findViewById(R.id.hide_button);
hideButton.setOnClickListener(v -> {
    BaseActivity activity = (BaseActivity) getActivity();
    if (activity != null) activity.hideLoader();
});
```

**What Happens:** The loader hides when the button is clicked.

**When to Use:** When user interaction is required to hide the loader.

---

### Hiding after a Background Task

```java
Executors.newSingleThreadExecutor().execute(() -> {
    try {
        Thread.sleep(2000);  // Simulate background work
    } catch (InterruptedException e) {
        // Ignore error
    }
    getActivity().runOnUiThread(() -> {
        BaseActivity activity = (BaseActivity) getActivity();
        if (activity != null) activity.hideLoader();
    });
});
```

**What Happens:** The loader hides when the background task is complete.

**When to Use:** For tasks that run in the background.

## Example 1: Basic Loader with a Timer
Show a simple loader and hide it after a few seconds.

```java
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import androidx.fragment.app.Fragment;
import com.oopgroup.smartpharmacy.utils.BaseActivity;

public class SampleFragment extends Fragment {

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        showLoader();
        new Handler().postDelayed(this::hideLoader, 3000);
    }

    private void showLoader() {
        BaseActivity activity = (BaseActivity) getActivity();
        if (activity != null) activity.showDefaultLoader();
    }

    private void hideLoader() {
        BaseActivity activity = (BaseActivity) getActivity();
        if (activity != null) activity.hideLoader();
    }
}
```

**What Happens:** A loader appears for 3 seconds and then disappears.

**When to Use:** For quick tasks.

**How to Hide:** Wait 3 seconds, and it hides automatically.

---

## Example 2: Loader with Custom Color
Show a loader with a different color and hide it after a delay.

```java
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import androidx.fragment.app.Fragment;
import com.oopgroup.smartpharmacy.utils.BaseActivity;

public class SampleFragment extends Fragment {

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        showLoader();
        new Handler().postDelayed(this::hideLoader, 4000);
    }

    private void showLoader() {
        BaseActivity activity = (BaseActivity) getActivity();
        if (activity != null) {
            BaseActivity.LoaderConfig config = new BaseActivity.LoaderConfig()
                .setAnimationResId(R.raw.loading_global)
                .setWidthDp(50)
                .setHeightDp(50)
                .setChangeJsonColor(true)
                .setJsonColor(Color.RED)
                .setOverlayColor(Color.parseColor("#80000000"));
            activity.showCustomLoader(config);
        }
    }

    private void hideLoader() {
        BaseActivity activity = (BaseActivity) getActivity();
        if (activity != null) activity.hideLoader();
    }
}
```

**What Happens:** A 50x50 red loader with a dark gray background shows for 4 seconds.

**When to Use:** To match your app’s colors or make the loader stand out.

**How to Hide:** Wait 4 seconds, and it fades away.

---

## Example 3: Simple Loader Without Background
Show a loader with no background or extra box, then hide it.

```java
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import androidx.fragment.app.Fragment;
import com.oopgroup.smartpharmacy.utils.BaseActivity;

public class SampleFragment extends Fragment {

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        showLoader();
        new Handler().postDelayed(this::hideLoader, 3000);
    }

    private void showLoader() {
        BaseActivity activity = (BaseActivity) getActivity();
        if (activity != null) {
            BaseActivity.LoaderConfig config = new BaseActivity.LoaderConfig()
                .setAnimationResId(R.raw.custom_spinner)
                .setWidthDp(80)
                .setHeightDp(80)
                .setUseRoundedBox(false)
                .setOverlayColor(Color.TRANSPARENT);
            activity.showCustomLoader(config);
        }
    }

    private void hideLoader() {
        BaseActivity activity = (BaseActivity) getActivity();
        if (activity != null) activity.hideLoader();
    }
}
```

**What Happens:** A big 80x80 loader spins for 3 seconds with no background or box.

**When to Use:** For a clean look without covering the screen.

**How to Hide:** Wait 3 seconds, and it’s gone.

---

## Example 4: Loader with Buttons
Show and hide the loader by tapping buttons.

```java
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.fragment.app.Fragment;
import com.oopgroup.smartpharmacy.utils.BaseActivity;

public class SampleFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sample, container, false);
        Button showButton = view.findViewById(R.id.show_button);
        Button hideButton = view.findViewById(R.id.hide_button);

        showButton.setOnClickListener(v -> showLoader());
        hideButton.setOnClickListener(v -> hideLoader());
        
        return view;
    }

    private void showLoader() {
        BaseActivity activity = (BaseActivity) getActivity();
        if (activity != null) {
            BaseActivity.LoaderConfig config = new BaseActivity.LoaderConfig()
                .setAnimationResId(R.raw.loading_global)
                .setWidthDp(40)
                .setHeightDp(40);
            activity.showCustomLoader(config);
        }
    }

    private void hideLoader() {
        BaseActivity activity = (BaseActivity) getActivity();
        if (activity != null) activity.hideLoader();
    }
}
```

Layout (fragment_sample.xml):
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:gravity="center">

    <Button
        android:id="@+id/show_button"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Show Loader" />

    <Button
        android:id="@+id/hide_button"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Hide Loader" />
</LinearLayout>
```

**What Happens:** Tap "Show Loader" to see it, and "Hide Loader" to make it go away.

**When to Use:** When users control when the loader appears, like starting a task.

**How to Hide:** Tap the "Hide Loader" button.

---

## Example 5: Loader for a Background Task
Show the loader while something runs in the background.

```java
import android.os.Bundle;
import android.view.View;
import androidx.fragment.app.Fragment;
import com.oopgroup.smartpharmacy.utils.BaseActivity;
import java.util.concurrent.Executors;

public class SampleFragment extends Fragment {

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        showLoader();
        Executors.newSingleThreadExecutor().execute(() -> {
            try {
                Thread.sleep(2000);  // Wait 2 seconds
            } catch (InterruptedException e) {
                // Ignore error
            }
            getActivity().runOnUiThread(this::hideLoader);  // Hide when done
        });
    }

    private void showLoader() {
        BaseActivity activity = (BaseActivity) getActivity();
        if (activity != null) {
            BaseActivity.LoaderConfig config = new BaseActivity.LoaderConfig()
                .setAnimationResId(R.raw.loading_dots)
                .setWidthDp(60)
                .setHeightDp(60);
            activity.showCustomLoader(config);
        }
    }

    private void hideLoader() {
        BaseActivity activity = (BaseActivity) getActivity();
        if (activity != null) activity.hideLoader();
    }
}
```

**What Happens:** A 60x60 loader shows for 2 seconds while a fake task runs.

**When to Use:** For things like downloading data from the internet.

**How to Hide:** It hides automatically when the task finishes.

---

## Example 6: Loader with Speed Change
Show a loader that speeds up after a bit.

```java
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import androidx.fragment.app.Fragment;
import com.oopgroup.smartpharmacy.utils.BaseActivity;

public class SampleFragment extends Fragment {

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        showLoader();
        new Handler().postDelayed(() -> {
            BaseActivity activity = (BaseActivity) getActivity();
            if (activity != null && activity.loaderAnimation != null) {
                activity.loaderAnimation.setSpeed(2.0f);  // Make it spin faster
            }
        }, 2000);
        new Handler().postDelayed(this::hideLoader, 4000);
    }

    private void showLoader() {
        BaseActivity activity = (BaseActivity) getActivity();
        if (activity != null) {
            BaseActivity.LoaderConfig config = new BaseActivity.LoaderConfig()
                .setAnimationResId(R.raw.loading_global)
                .setAnimationSpeed(1.0f);  // Start at normal speed
            activity.showCustomLoader(config);
        }
    }

    private void hideLoader() {
        BaseActivity activity = (BaseActivity) getActivity();
        if (activity != null) activity.hideLoader();
    }
}
```

**What Happens:** The loader spins normally for 2 seconds, then speeds up, and hides after 4 seconds.

**When to Use:** To show progress changing, like a task getting faster.

**How to Hide:** Wait 4 seconds, and it stops.

---

## Example 7: Loader with Messages
Show the loader and print messages when it starts and stops.

```java
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.fragment.app.Fragment;
import com.oopgroup.smartpharmacy.utils.BaseActivity;

public class SampleFragment extends Fragment {
    private static final String TAG = "SampleFragment";

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        BaseActivity activity = (BaseActivity) getActivity();
        if (activity != null) {
            activity.setLoaderCallback(new BaseActivity.LoaderCallback() {
                @Override
                public void onLoaderShown() {
                    Log.d(TAG, "Loader is showing now!");
                }

                @Override
                public void onLoaderHidden() {
                    Log.d(TAG, "Loader is gone!");
                }
            });
        }
        showLoader();
        new Handler().postDelayed(this::hideLoader, 3000);
    }

    private void showLoader() {
        BaseActivity activity = (BaseActivity) getActivity();
        if (activity != null) {
            BaseActivity.LoaderConfig config = new BaseActivity.LoaderConfig()
                .setAnimationResId(R.raw.loading_global);
            activity.showCustomLoader(config);
        }
    }

    private void hideLoader() {
        BaseActivity activity = (BaseActivity) getActivity();
        if (activity != null) activity.hideLoader();
    }
}
```

**What Happens:** The loader spins for 3 seconds, and messages show up in the log when it starts and stops.

**When to Use:** To check if the loader is working or to do something when it changes.

**How to Hide:** Wait 3 seconds, and it hides with a message.

---

## Example 8: Loader in a Specific Area
Put the loader in a certain part of the screen.

```java
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.fragment.app.Fragment;
import com.oopgroup.smartpharmacy.utils.BaseActivity;

public class SampleFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sample, container, false);
        FrameLayout area = view.findViewById(R.id.custom_area);
        showLoader(area);
        new Handler().postDelayed(this::hideLoader, 3000);
        return view;
    }

    private void showLoader(FrameLayout area) {
        BaseActivity activity = (BaseActivity) getActivity();
        if (activity != null) {
            BaseActivity.LoaderConfig config = new BaseActivity.LoaderConfig()
                .setAnimationResId(R.raw.loading_global)
                .setWidthDp(40)
                .setHeightDp(40);
            activity.showCustomLoader(config);
            if (activity.loaderOverlay != null) {
                area.addView(activity.loaderOverlay);  // Put it in the area
            }
        }
    }

    private void hideLoader() {
        BaseActivity activity = (BaseActivity) getActivity();
        if (activity != null) activity.hideLoader();
    }
}
```

Layout (fragment_sample.xml):
```xml
<?xml version="1.0" encoding="utf-8"?>
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">
    <FrameLayout
        android:id="@+id/custom_area"
        android:layout_width="match_parent"
        android:layout_height="200dp"
        android:background="#EEEEEE" />
</FrameLayout>
```

**What Happens:** A small loader spins in a gray box at the top of the screen for 3 seconds.

**When to Use:** To show loading in just one part of the screen, like a form.

**How to Hide:** Wait 3 seconds, and it disappears.

---

## Example 9: Old Way to Show Loader
Use the old method with lots of settings.

```java
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import androidx.fragment.app.Fragment;
import com.oopgroup.smartpharmacy.utils.BaseActivity;

public class SampleFragment extends Fragment {

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        showLoader();
        new Handler().postDelayed(this::hideLoader, 3000);
    }

    private void showLoader() {
        BaseActivity activity = (BaseActivity) getActivity();
        if (activity != null) {
            activity.showCustomLoader(
                R.raw.loading_dots,
                activity.dpToPx(50),
                activity.dpToPx(50),
                true,
                Color.parseColor("#AA00FF00"),
                true,
                Color.YELLOW,
                1.5f
            );
        }
    }

    private void hideLoader() {
        BaseActivity activity = (BaseActivity) getActivity();
        if (activity != null) activity.hideLoader();
    }
}
```

**What Happens:** A yellow loader with a green background spins fast for 3 seconds.

**When to Use:** If you’re using older code that hasn’t been updated yet.

**How to Hide:** Wait 3 seconds, and it stops.

---

## Example 10: Custom Loader in Activity

Show a custom loader with specific configurations in an activity.

```java
private void showCustomLoader() {
    LoaderConfig config = new LoaderConfig()
            .setAnimationResId(R.raw.loading_global)
            .setWidthDp(40)
            .setHeightDp(40)
            .setUseRoundedBox(true)
            .setOverlayColor(Color.parseColor("#80000000"))
            .setChangeJsonColor(false);
    showCustomLoader(config);
    Log.d(TAG, "Custom loader shown");
}
```

**What Happens:** A custom loader with the specified configurations is shown.

**When to Use:** When you need a loader with specific configurations in an activity.

**How to Hide:** Use the `hideLoader` method in the `BaseActivity`.

---

## Example 11: Custom Loader in Fragment

Show a custom loader with specific configurations in a fragment.

```java
private void showCustomLoader() {
    if (isAdded() && getActivity() instanceof BaseActivity) {
        BaseActivity baseActivity = (BaseActivity) getActivity();
        BaseActivity.LoaderConfig config = new BaseActivity.LoaderConfig()
                .setAnimationResId(R.raw.loading_global)
                .setWidthDp(40)
                .setHeightDp(40)
                .setUseRoundedBox(true)
                .setOverlayColor(Color.parseColor("#80000000"))
                .setChangeJsonColor(false);
        baseActivity.showCustomLoader(config);
        Log.d(TAG, "Custom loader shown");
    }
}

private void hideLoader() {
    if (isAdded() && requireActivity() instanceof BaseActivity) {
        BaseActivity baseActivity = (BaseActivity) requireActivity();
        baseActivity.hideLoader();
    }
}
```

**What Happens:** A custom loader with the specified configurations is shown in the fragment.

**When to Use:** When you need a loader with specific configurations in a fragment.

**How to Hide:** Use the `hideLoader` method as shown in the example.

---



---
## Recommendations and Customization Instructions

### Recommendations
1. **Keep Loaders Minimal**: Only use loaders when absolutely necessary to indicate ongoing processes.
2. **Match Loader Design with App Theme**: Ensure the loader’s design aligns with your app’s color scheme and overall design aesthetic.
3. **Use Appropriate Loader Sizes**: Adjust the size of the loader based on the context (e.g., larger loaders for full-screen operations, smaller ones for inline operations).

### Customization Instructions
1. **Change Loader Animation**: You can customize the loader animation by changing the `setAnimationResId` parameter in the `LoaderConfig` to another animation resource.
2. **Adjust Loader Size**: Modify the `setWidthDp` and `setHeightDp` parameters in the `LoaderConfig` to change the loader's dimensions.
3. **Change Loader Color**: Use the `setChangeJsonColor` and `setJsonColor` parameters to customize the loader’s color.
4. **Modify Overlay Color**: Adjust the `setOverlayColor` parameter to change the background color of the loader overlay.
5. **Remove Rounded Box**: Set `setUseRoundedBox` to `false` if you prefer the loader without a rounded box around it.


### ✅ Hide Loader
```java
((BaseActivity) getActivity()).hideGlobalLoader();
```

---

## 📋 Code Explanation

The `BaseActivity.java` class provides a reusable Lottie-based loading overlay with the following features:

- **Resource Validation**: The `isValidResource()` method checks if the specified Lottie animation resource exists in `res/raw/`. If not, it falls back to a default animation (`R.raw.default_loader`).
- **Dynamic Sizing**: The `dpToPx()` method converts DP values to pixels for consistent sizing across devices.
- **Modern Color Filtering**: Uses Lottie’s `addValueCallback` to dynamically change the animation’s color across all layers (`KeyPath("**")`).
- **Smooth Transitions**: The loader overlay fades in and out with a 250ms animation for a polished user experience.
- **Touch Blocking**: The overlay blocks all touch events behind it, ensuring the user cannot interact with the UI while the loader is visible.
- **Dynamic Background Sizing**: When `useRoundedBox` is enabled, the background size adjusts dynamically based on the Lottie animation’s dimensions, with added padding for a clean look.
- **Error Handling**: Catches and logs exceptions during loader setup, ensuring the app doesn’t crash if something goes wrong.
- **Cleanup**: Properly cleans up resources in `onDestroy()` to prevent memory leaks.

### Key Methods and Their Roles:

- `dpToPx(int dp)`: Converts density-independent pixels (dp) to pixels for consistent sizing across different screen densities.
- `isValidResource(int resId)`: Validates if a Lottie animation resource exists.
- `isDarkMode()`: Checks if the device is in dark mode (useful for theme-aware colors).
- `showDefaultLoader()`: Displays a default Lottie loader with preset configurations.
- `showCustomLoader(LoaderConfig config)`: Displays a customizable Lottie loader based on the provided `LoaderConfig` object.
- `startLoaderTimeout()`: Starts a timeout to auto-hide the loader after a specified duration (`MAX_LOADER_DURATION_MS`).
- `initializeLoaderOverlay(int animationResId, int width, int height, boolean useRoundedBox, int overlayColor, boolean changeJsonColor, int jsonColor, float animationSpeed)`: Initializes the loader overlay and animation view with the specified configuration.
- `createAnimationContainer(boolean useRoundedBox, int width, int height)`: Creates a container for the animation, optionally adding a rounded box background.
- `createLottieAnimationView(int animationResId, boolean changeJsonColor, int jsonColor, float animationSpeed)`: Creates the Lottie animation view and sets its properties.
- `addOverlayToRootView(FrameLayout overlay, int overlayColor)`: Adds the overlay to the root view of the activity.
- `updateLoaderOverlay(int animationResId, int overlayColor, boolean changeJsonColor, int jsonColor, float animationSpeed)`: Updates the existing loader overlay and animation view with new configurations.
- `hideLoader()`: Hides the Lottie loader with a fade-out animation.
- `cleanupLoader()`: Cleans up the loader resources and resets the state to prevent memory leaks.


---

## 🤝 Contributing
Contributions are what make the open-source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

```bash
# Example commands for contributing
git clone https://github.com/ashrafulwpdev/GlobalLottieLoader.git
cd GlobalLottieLoader
git checkout -b feature/AmazingFeature
# Make your changes
git add .
git commit -m 'Add some AmazingFeature'
git push origin feature/AmazingFeature
# Open a Pull Request via GitHub
```

---

## 🐞 Issues
If you encounter any issues, please feel free to [open an issue](https://github.com/ashrafulwpdev/GlobalLottieLoader/issues).

---

## 👨‍💻 Developed By
**Company:** Softourtech  
**Developer:** [Ashraful Islam](https://github.com/ashrafulwpdev)

Building reusable and efficient solutions for Android developers!

---

## 📜 License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
