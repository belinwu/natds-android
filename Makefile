clean:
	./gradlew clean

lint:
	./gradlew ktlint

run-lint-rules:
	./gradlew ktlintFormat

unit-test: unit-test-designsystem unit-test-sample

unit-test-sample:
	./gradlew sample:test --parallel

unit-test-designsystem:
	./gradlew designsystem:test --parallel

instrumentation-test: clean disable-animations
	./gradlew connectedAndroidTest

screenshot-test: clean install-tools-screenshot-test
	./gradlew verifyDebugAndroidTestScreenshotTest

update-screenshots: clean
	./gradlew recordDebugAndroidTestScreenshotTest

install-tools-screenshot-test:
	bash ./tools/screenshot-tools.sh

disable-animations:
	adb shell settings put global window_animation_scale 0
	adb shell settings put global transition_animation_scale 0
	adb shell settings put global animator_duration_scale 0

enable-animations:
	adb shell settings put global window_animation_scale 1
	adb shell settings put global transition_animation_scale 1
	adb shell settings put global animator_duration_scale 1

publish-ds-lib-prod: clean
	./gradlew build publish --stacktrace

run-all-sanitycheck-steps: lint unit-test instrumentation-test
