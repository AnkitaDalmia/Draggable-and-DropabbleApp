/*
 * Copyright 2015, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
*/

package com.example.android.dragsourcetwo;

import com.example.android.common.logger.Log;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v13.view.DragStartHelper;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;


public class DragSourceFragment extends Fragment {

    /**
     * Name of saved data that stores the dropped image URI on the local ImageView when set.
     */
    private static final String IMAGE_URI = "IMAGE_URI";

    /**
     * Name of the parameter for a {@link ClipData} extra that stores a text describing the dragged
     * image.
     */
    public static final String EXTRA_IMAGE_INFO = "IMAGE_INFO";

    /**
     * Uri of the ImageView source when set.
     */
    private Uri mLocalImageUri;

    private static final String TAG = "DragSourceFragment";

    private static final String CONTENT_AUTHORITY = "com.example.android.dragsourcetwo.fileprovider";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dragsourcetwo, null);

        // Set up two image views for global drag and drop with a permission grant.


       Uri  imageUri = getFileUri(R.drawable.cam, "cam.png");
     ImageView imageView = (ImageView) view.findViewById(R.id.imageView2);
        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent i =new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(i);


            }
        });
        setUpDraggableImage(imageView, imageUri);
        imageView.setImageURI(imageUri);

        imageUri = getFileUri(R.drawable.pi1, "pi1.png");
        imageView = (ImageView) view.findViewById(R.id.imageView7);
        setUpDraggableImage(imageView, imageUri);
        imageView.setImageURI(imageUri);



        imageUri = getFileUri(R.drawable.dell1, "dell1.jpg");
        imageView = (ImageView) view.findViewById(R.id.imageView3);
        setUpDraggableImage(imageView, imageUri);
        imageView.setImageURI(imageUri);

        imageUri = getFileUri(R.drawable.bus, "bus.png");
        imageView = (ImageView) view.findViewById(R.id.imageView6);
        setUpDraggableImage(imageView, imageUri);
        imageView.setImageURI(imageUri);

        imageUri = getFileUri(R.drawable.clock, "clock.png");
        imageView = (ImageView) view.findViewById(R.id.imageView5);
        setUpDraggableImage(imageView, imageUri);
        imageView.setImageURI(imageUri);



        imageUri = getFileUri(R.drawable.call1, "call1.jpg");
        imageView = (ImageView) view.findViewById(R.id.imageView3);
        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
              String s="8420364387";
              Uri u=Uri.parse("tel:" +s);
                Intent i =new Intent(Intent.ACTION_VIEW,u);
                startActivity(i);


            }
        });
        setUpDraggableImage(imageView, imageUri);
        imageView.setImageURI(imageUri);

        imageUri = getFileUri(R.drawable.gmap, "gmap.png");
        imageView = (ImageView) view.findViewById(R.id.imageView4);
        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String s="KIIT UNIVERSITY";
                Uri u=Uri.parse("geo:?q="+s);
                Intent i = new Intent(Intent.ACTION_VIEW, u);
                startActivity(i);


            }
        });
        setUpDraggableImage(imageView, imageUri);
        imageView.setImageURI(imageUri);

        imageUri = getFileUri(R.drawable.graph, "graph.png");
        imageView = (ImageView) view.findViewById(R.id.imageView11);
        setUpDraggableImage(imageView, imageUri);
        imageView.setImageURI(imageUri);
        // Set up the local drop target area.
        final ImageView localImageTarget = (ImageView) view.findViewById(R.id.local_target);

        localImageTarget.setOnDragListener(new ImageDragListener() {
            @Override
            protected boolean setImageUri(View view, DragEvent event, Uri uri) {
                mLocalImageUri = uri;

                Log.d(TAG, "Setting local image to: " + uri);
                return super.setImageUri(view, event, uri);
            }
        });

        if (savedInstanceState != null) {
            final String uriString = savedInstanceState.getString(IMAGE_URI);
            if (uriString != null) {
                mLocalImageUri = Uri.parse(uriString);
                Log.d(TAG, "Restoring local image to: " + mLocalImageUri);
                localImageTarget.setImageURI(mLocalImageUri);
            }
        }

        final ImageView localImageTarget2 = (ImageView) view.findViewById(R.id.local_target2);
        localImageTarget2.setOnDragListener(new ImageDragListener() {
            @Override
            protected boolean setImageUri(View view, DragEvent event, Uri uri) {
                mLocalImageUri = uri;
                Log.d(TAG, "Setting local image to: " + uri);
                return super.setImageUri(view, event, uri);
            }
        });
        if (savedInstanceState != null) {
            final String uriString = savedInstanceState.getString(IMAGE_URI);
            if (uriString != null) {
                mLocalImageUri = Uri.parse(uriString);
                Log.d(TAG, "Restoring local image to: " + mLocalImageUri);
                localImageTarget2.setImageURI(mLocalImageUri);
            }
        }
        final ImageView localImageTarget3 = (ImageView) view.findViewById(R.id.local_target3);
        localImageTarget3.setOnDragListener(new ImageDragListener() {
            @Override
            protected boolean setImageUri(View view, DragEvent event, Uri uri) {
                mLocalImageUri = uri;
                Log.d(TAG, "Setting local image to: " + uri);
                return super.setImageUri(view, event, uri);
            }
        });
        if (savedInstanceState != null) {
            final String uriString = savedInstanceState.getString(IMAGE_URI);
            if (uriString != null) {
                mLocalImageUri = Uri.parse(uriString);
                Log.d(TAG, "Restoring local image to: " + mLocalImageUri);
                localImageTarget3.setImageURI(mLocalImageUri);
            }
        }

        final ImageView localImageTarget4 = (ImageView) view.findViewById(R.id.local_target4);
        localImageTarget4.setOnDragListener(new ImageDragListener() {
            @Override
            protected boolean setImageUri(View view, DragEvent event, Uri uri) {
                mLocalImageUri = uri;
                Log.d(TAG, "Setting local image to: " + uri);
                return super.setImageUri(view, event, uri);
            }
        });





        if (savedInstanceState != null) {
            final String uriString = savedInstanceState.getString(IMAGE_URI);
            if (uriString != null) {
                mLocalImageUri = Uri.parse(uriString);
                Log.d(TAG, "Restoring local image to: " + mLocalImageUri);
                localImageTarget4.setImageURI(mLocalImageUri);
            }
        }
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        if (mLocalImageUri != null) {
            savedInstanceState.putString(IMAGE_URI, mLocalImageUri.toString());
        }
        super.onSaveInstanceState(savedInstanceState);
    }

    private void setUpDraggableImage(ImageView imageView, final Uri imageUri) {

        // Set up a listener that starts the drag and drop event with flags and extra data.
        DragStartHelper.OnDragStartListener listener = new DragStartHelper.OnDragStartListener() {
            @Override
            public boolean onDragStart(View view, final DragStartHelper helper) {
                Log.d(TAG, "Drag start event received from helper.");

                // Use a DragShadowBuilder
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view) {
                    @Override
                    public void onProvideShadowMetrics(Point shadowSize, Point shadowTouchPoint) {
                        super.onProvideShadowMetrics(shadowSize, shadowTouchPoint);
                        // Notify the DragStartHelper of point where the view was touched.
                        helper.getTouchPosition(shadowTouchPoint);
                        Log.d(TAG, "View was touched at: " + shadowTouchPoint);
                    }
                };

                // Set up the flags for the drag event.
                // Enable drag and drop across apps (global)
                // and require read permissions for this URI.
                int flags = View.DRAG_FLAG_GLOBAL | View.DRAG_FLAG_GLOBAL_URI_READ;

                // Add an optional clip description that that contains an extra String that is
                // read out by the target app.
                final ClipDescription clipDescription = new ClipDescription("", new String[]{
                        getContext().getContentResolver().getType(imageUri)});
                // Extras are stored within a PersistableBundle.
                PersistableBundle extras = new PersistableBundle(1);
                // Add a String that the target app will display.
                extras.putString(EXTRA_IMAGE_INFO,
                        "Drag Started at " + new Date());
                clipDescription.setExtras(extras);

                // The ClipData object describes the object that is being dragged and dropped.
                final ClipData clipData =
                        new ClipData(clipDescription, new ClipData.Item(imageUri));

                Log.d(TAG, "Created ClipDescription. Starting drag and drop.");
                // Start the drag and drop event.
                return view.startDragAndDrop(clipData, shadowBuilder, null, flags);

            }

        };

        // Use the DragStartHelper to detect drag and drop events and use the OnDragStartListener
        // defined above to start the event when it has been detected.
        DragStartHelper helper = new DragStartHelper(imageView, listener);
        helper.attach();
        Log.d(TAG, "DragStartHelper attached to view.");
    }


    private Uri getFileUri(int sourceResourceId, String targetName) {
        // Create the images/ sub directory if it does not exist yet.
        File filePath = new File(getContext().getFilesDir(), "images");
        if (!filePath.exists() && !filePath.mkdir()) {
            return null;
        }

        // Copy a drawable from resources to the internal directory.
        File newFile = new File(filePath, targetName);
        if (!newFile.exists()) {
            copyImageResourceToFile(sourceResourceId, newFile);
        }

        // Make the file accessible via the FileProvider and retrieve its URI.
        return FileProvider.getUriForFile(getContext(), CONTENT_AUTHORITY, newFile);
    }


    /**
     * Copy a PNG resource drawable to a {@File}.
     */
    private void copyImageResourceToFile(int resourceId, File filePath) {
        Bitmap image = BitmapFactory.decodeResource(getResources(), resourceId);

        FileOutputStream out = null;
        try {
            out = new FileOutputStream(filePath);
            image.compress(Bitmap.CompressFormat.PNG, 100, out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
