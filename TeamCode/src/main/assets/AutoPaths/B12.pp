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
        "heading": "linear",
        "startDeg": 140,
        "endDeg": 150,
        "reverse": true
      },
      "controlPoints": [],
      "color": "#58CCA5",
      "eventMarkers": [],
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
        "x": 42,
        "y": 85,
        "heading": "linear",
        "reverse": false,
        "degrees": 180,
        "startDeg": 150,
        "endDeg": 180
      },
      "controlPoints": [],
      "color": "#CA7BBC",
      "waitBeforeMs": 0,
      "waitAfterMs": 0,
      "waitBeforeName": "",
      "waitAfterName": "",
      "eventMarkers": []
    },
    {
      "id": "mlnwf26u-wiznrb",
      "name": "topDone",
      "endPoint": {
        "x": 12,
        "y": 85,
        "heading": "constant",
        "reverse": false,
        "degrees": 180
      },
      "controlPoints": [],
      "color": "#868D67",
      "waitBeforeMs": 0,
      "waitAfterMs": 0,
      "waitBeforeName": "",
      "waitAfterName": "",
      "eventMarkers": []
    },
    {
      "id": "mlraf7y0-mkcp52",
      "name": "shootTop",
      "endPoint": {
        "x": 42,
        "y": 97,
        "heading": "linear",
        "reverse": false,
        "startDeg": 180,
        "endDeg": 150
      },
      "controlPoints": [],
      "color": "#ACBB55",
      "waitBeforeMs": 0,
      "waitAfterMs": 0,
      "waitBeforeName": "",
      "waitAfterName": ""
    },
    {
      "id": "mlrajy0n-74ismr",
      "name": "middleBegin",
      "endPoint": {
        "x": 42,
        "y": 60.3059892137383,
        "heading": "linear",
        "reverse": false,
        "startDeg": 150,
        "endDeg": 180
      },
      "controlPoints": [],
      "color": "#7968BC",
      "waitBeforeMs": 0,
      "waitAfterMs": 0,
      "waitBeforeName": "",
      "waitAfterName": ""
    },
    {
      "id": "mlrakdty-4ntk4n",
      "name": "middleDone",
      "endPoint": {
        "x": 14.413851830826,
        "y": 60.3059892137383,
        "heading": "constant",
        "reverse": false,
        "degrees": 180
      },
      "controlPoints": [],
      "color": "#7B9AB8",
      "waitBeforeMs": 0,
      "waitAfterMs": 0,
      "waitBeforeName": "",
      "waitAfterName": ""
    },
    {
      "id": "mlrakq7b-u8vbqo",
      "name": "shootMiddle",
      "endPoint": {
        "x": 42,
        "y": 97,
        "heading": "linear",
        "reverse": false,
        "startDeg": 180,
        "endDeg": 150
      },
      "controlPoints": [
        {
          "x": 45,
          "y": 69
        }
      ],
      "color": "#5B6D68",
      "waitBeforeMs": 0,
      "waitAfterMs": 0,
      "waitBeforeName": "",
      "waitAfterName": ""
    },
    {
      "id": "mlral29s-ux09q8",
      "name": "bottomBegin",
      "endPoint": {
        "x": 42,
        "y": 36.17456713028668,
        "heading": "linear",
        "reverse": false,
        "startDeg": 150,
        "endDeg": 180
      },
      "controlPoints": [],
      "color": "#C5989B",
      "waitBeforeMs": 0,
      "waitAfterMs": 0,
      "waitBeforeName": "",
      "waitAfterName": ""
    },
    {
      "id": "mlraliam-tw381o",
      "name": "bottomEnd",
      "endPoint": {
        "x": 14.413851830826,
        "y": 36.17456713028668,
        "heading": "constant",
        "reverse": false,
        "degrees": 180
      },
      "controlPoints": [],
      "color": "#C5ACA5",
      "waitBeforeMs": 0,
      "waitAfterMs": 0,
      "waitBeforeName": "",
      "waitAfterName": ""
    },
    {
      "id": "mlrammu6-tje1xt",
      "name": "shootBottom",
      "endPoint": {
        "x": 42,
        "y": 97,
        "heading": "linear",
        "reverse": false,
        "startDeg": 180,
        "endDeg": 150
      },
      "controlPoints": [],
      "color": "#975558",
      "waitBeforeMs": 0,
      "waitAfterMs": 0,
      "waitBeforeName": "",
      "waitAfterName": ""
    },
    {
      "id": "mlramwwr-zeyp5i",
      "name": "toGate",
      "endPoint": {
        "x": 19.69003690036901,
        "y": 65.42435424354244,
        "heading": "linear",
        "reverse": false,
        "startDeg": 150,
        "endDeg": 180
      },
      "controlPoints": [],
      "color": "#5DCBD5",
      "waitBeforeMs": 0,
      "waitAfterMs": 0,
      "waitBeforeName": "",
      "waitAfterName": ""
    }
  ],
  "sequence": [
    {
      "kind": "path",
      "lineId": "line-ogq85cisbbb"
    },
    {
      "kind": "wait",
      "id": "mlrazf5e-k4071r",
      "name": "wait1",
      "durationMs": 500,
      "locked": false
    },
    {
      "kind": "path",
      "lineId": "mlra8lxv-45ytzk"
    },
    {
      "kind": "wait",
      "id": "mlrazlv4-hwsv3a",
      "name": "wait2",
      "durationMs": 500,
      "locked": false
    },
    {
      "kind": "path",
      "lineId": "mlnwf26u-wiznrb"
    },
    {
      "kind": "wait",
      "id": "mlrb044p-j98gjd",
      "name": "wait3",
      "durationMs": 500,
      "locked": false
    },
    {
      "kind": "path",
      "lineId": "mlraf7y0-mkcp52"
    },
    {
      "kind": "wait",
      "id": "mlrb0axd-m8je01",
      "name": "wait4",
      "durationMs": 500,
      "locked": false
    },
    {
      "kind": "path",
      "lineId": "mlrajy0n-74ismr"
    },
    {
      "kind": "wait",
      "id": "mlrb0ekn-mwhgbr",
      "name": "wait5",
      "durationMs": 500,
      "locked": false
    },
    {
      "kind": "path",
      "lineId": "mlrakdty-4ntk4n"
    },
    {
      "kind": "wait",
      "id": "mlrb0ijj-2q9nvl",
      "name": "wait6",
      "durationMs": 500,
      "locked": false
    },
    {
      "kind": "path",
      "lineId": "mlrakq7b-u8vbqo"
    },
    {
      "kind": "wait",
      "id": "mlrb0m6i-gbq5nf",
      "name": "wait7",
      "durationMs": 500,
      "locked": false
    },
    {
      "kind": "path",
      "lineId": "mlral29s-ux09q8"
    },
    {
      "kind": "wait",
      "id": "mlrb0pgb-iof2k9",
      "name": "wait8",
      "durationMs": 500,
      "locked": false
    },
    {
      "kind": "path",
      "lineId": "mlraliam-tw381o"
    },
    {
      "kind": "wait",
      "id": "mlrb0tpu-11lwkt",
      "name": "wait9",
      "durationMs": 500,
      "locked": false
    },
    {
      "kind": "path",
      "lineId": "mlrammu6-tje1xt"
    },
    {
      "kind": "wait",
      "id": "mlrb0ws0-cha2o7",
      "name": "wait10",
      "durationMs": 500,
      "locked": false
    },
    {
      "kind": "path",
      "lineId": "mlramwwr-zeyp5i"
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