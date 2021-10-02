# jfr-socket-file-stats-demo

## Why?

You can observe the (file|socket)'s (reading|writing) bytes by JFR event streaming.
This repository will show you how to use it.

## How it works?

There are `jdk.SocketRead`, `jdk.SocketWrite`, `jdk.FileRead` and `jdk.FileWrite` events in JFR.
You can observe these events via `RecordingStream` class.
And expose it to micrometer.

## When is this method useful?

- The application causes tons of file IO
- The application causes tons of socket IO
