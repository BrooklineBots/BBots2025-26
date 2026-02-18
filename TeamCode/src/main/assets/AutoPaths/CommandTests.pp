{
  "version": "1.7.3",
  "header": {
    "info": "Created with Pedro Pathing Plus Visualizer",
    "copyright": "Copyright 2026 Matthew Allen. Licensed under the Apache License, Version 2.0.",
    "link": "https://github.com/Mallen220/PedroPathingPlusVisualizer"
  },
  "startPoint": {
    "x": 56,
    "y": 8,
    "heading": "linear",
    "startDeg": 90,
    "endDeg": 180,
    "locked": false
  },
  "lines": [
    {
      "id": "line-t23zg099i7a",
      "name": "",
      "endPoint": {
        "x": 28.988929889298895,
        "y": 95.45387453874538,
        "heading": "linear",
        "startDeg": -90,
        "endDeg": -90
      },
      "controlPoints": [],
      "color": "#D87978",
      "eventMarkers": [],
      "locked": false,
      "waitBeforeMs": 0,
      "waitAfterMs": 0,
      "waitBeforeName": "",
      "waitAfterName": ""
    }
  ],
  "sequence": [
    {
      "kind": "path",
      "lineId": "line-t23zg099i7a"
    },
    {
      "kind": "wait",
      "id": "mlr5s17q-7ocfqm",
      "name": "wait",
      "durationMs": 1000,
      "locked": false,
      "eventMarkers": [
        {
          "id": "event-1771365146904-auopvfm2o",
          "name": "IntakeOff",
          "position": 0.98,
          "waitId": "mlr5s17q-7ocfqm"
        },
        {
          "id": "event-1771365169251-j0wk2ypg6",
          "name": "IntakeOn",
          "position": 0,
          "waitId": "mlr5s17q-7ocfqm"
        }
      ]
    }
  ],
  "shapes": [
    {
      "id": "triangle-1",
      "name": "Red Goal",
      "vertices": [
        {
          "x": 144,
          "y": 70
        },
        {
          "x": 144,
          "y": 144
        },
        {
          "x": 118,
          "y": 144
        },
        {
          "x": 138,
          "y": 118
        },
        {
          "x": 138,
          "y": 70
        }
      ],
      "color": "#dc2626",
      "fillColor": "#fca5a5"
    },
    {
      "id": "triangle-2",
      "name": "Blue Goal",
      "vertices": [
        {
          "x": 7,
          "y": 118
        },
        {
          "x": 26,
          "y": 144
        },
        {
          "x": 0,
          "y": 144
        },
        {
          "x": 0,
          "y": 70
        },
        {
          "x": 7,
          "y": 70
        }
      ],
      "color": "#0b08d9",
      "fillColor": "#fca5a5"
    }
  ],
  "extraData": {}
}