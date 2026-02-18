{
  "version": "1.7.3",
  "header": {
    "info": "Created with Pedro Pathing Plus Visualizer",
    "copyright": "Copyright 2026 Matthew Allen. Licensed under the Apache License, Version 2.0.",
    "link": "https://github.com/Mallen220/PedroPathingPlusVisualizer"
  },
  "startPoint": {
    "x": 17.476014760147603,
    "y": 120.69372693726935,
    "heading": "linear",
    "startDeg": 90,
    "endDeg": 180,
    "locked": false
  },
  "lines": [
    {
      "id": "line-ogq85cisbbb",
      "name": "shootPreload",
      "endPoint": {
        "x": 42,
        "y": 97,
        "heading": "tangential",
        "startDeg": 90,
        "endDeg": -135,
        "reverse": true
      },
      "controlPoints": [],
      "color": "#58CCA5",
      "eventMarkers": [
        {
          "id": "event-1771280438719-w5a2eve2j",
          "name": "ShootCenter",
          "position": 0,
          "lineIndex": 0
        }
      ],
      "locked": false,
      "waitBeforeMs": 0,
      "waitAfterMs": 0,
      "waitBeforeName": "",
      "waitAfterName": ""
    },
    {
      "id": "mlra8lxv-45ytzk",
      "name": "topStart",
      "endPoint": {
        "x": 38.06273062730627,
        "y": 83.03321033210332,
        "heading": "tangential",
        "reverse": false
      },
      "controlPoints": [],
      "color": "#CA7BBC",
      "waitBeforeMs": 0,
      "waitAfterMs": 0,
      "waitBeforeName": "",
      "waitAfterName": ""
    },
    {
      "id": "mlnwf26u-wiznrb",
      "name": "topDone",
      "endPoint": {
        "x": 14.243542435424352,
        "y": 90.46494464944651,
        "heading": "tangential",
        "reverse": false
      },
      "controlPoints": [],
      "color": "#868D67",
      "waitBeforeMs": 0,
      "waitAfterMs": 0,
      "waitBeforeName": "",
      "waitAfterName": "",
      "eventMarkers": [
        {
          "id": "event-1771280454742-ww9bank8i",
          "name": "IntakeOff",
          "position": 0.08999999999999986,
          "lineIndex": 1
        }
      ]
    }
  ],
  "sequence": [
    {
      "kind": "path",
      "lineId": "line-ogq85cisbbb"
    },
    {
      "kind": "wait",
      "id": "mlnwe56f-s8k6r0",
      "name": "outtakePreload",
      "durationMs": 3000,
      "locked": false,
      "eventMarkers": [
        {
          "id": "event-1771280445511-58xqc1u0k",
          "name": "IntakeOn",
          "position": 0,
          "waitId": "mlnwe56f-s8k6r0"
        }
      ]
    },
    {
      "kind": "path",
      "lineId": "mlra8lxv-45ytzk"
    },
    {
      "kind": "path",
      "lineId": "mlnwf26u-wiznrb"
    },
    {
      "kind": "wait",
      "id": "mlnwfgui-ndrm4m",
      "name": "intakeTopWait",
      "durationMs": 500,
      "locked": false,
      "eventMarkers": []
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
      "fillColor": "#fca5a5",
      "type": "obstacle"
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
      "fillColor": "#fca5a5",
      "type": "obstacle"
    }
  ],
  "extraData": {}
}