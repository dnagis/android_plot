/*
 * Copyright 2015 AndroidPlot.com
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.androidplot.ui;

import androidx.annotation.NonNull;

public class PositionMetrics implements Comparable<PositionMetrics> {

    private HorizontalPosition horizontalPosition;
    private VerticalPosition verticalPosition;
    private Anchor anchor;
    private float layerDepth;

    public PositionMetrics(float x, HorizontalPositioning horizontalPositioning, float y, VerticalPositioning verticalPositioning, Anchor anchor) {
        setXPositionMetric(new HorizontalPosition(x, horizontalPositioning));
        setYPositionMetric(new VerticalPosition(y, verticalPositioning));
        setAnchor(anchor);

    }

    public VerticalPosition getYPositionMetric() {
        return verticalPosition;
    }

    public void setYPositionMetric(VerticalPosition verticalPosition) {
        this.verticalPosition = verticalPosition;
    }

    public Anchor getAnchor() {
        return anchor;
    }

    public void setAnchor(Anchor anchor) {
        this.anchor = anchor;
    }

    @Override
    public int compareTo(@NonNull PositionMetrics o) {
        if(this.layerDepth < o.layerDepth) {
            return -1;
        } else if(this.layerDepth == o.layerDepth) {
            return 0;
        } else {
            return 1;
        }
    }

    public HorizontalPosition getXPositionMetric() {
        return horizontalPosition;
    }

    public void setXPositionMetric(HorizontalPosition horizontalPosition) {
        this.horizontalPosition = horizontalPosition;
    }
}
