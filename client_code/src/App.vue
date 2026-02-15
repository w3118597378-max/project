<template>
	<router-view />
</template>
<script>
	const debounce = (fn, delay) => {
		let timer = null;
		return function() {
			let context = this;
			let args = arguments;
			clearTimeout(timer);
			timer = setTimeout(function() {
				fn.apply(context, args);
			}, delay);
		}
	}

	const _ResizeObserver = window.ResizeObserver;
	window.ResizeObserver = class ResizeObserver extends _ResizeObserver {
		constructor(callback) {
			callback = debounce(callback, 16);
			super(callback);
		}
	}
</script>
<style lang="scss">
	body {
		margin: 0;
		padding: 0;
		background-color: var(--slate-50);
	}
	* {
		box-sizing: border-box;
	}

	#app {
		font-family: Avenir, Helvetica, Arial, sans-serif;
		-webkit-font-smoothing: antialiased;
		-moz-osx-font-smoothing: grayscale;
		color: var(--slate-700);
		background-color: var(--slate-50);
		min-height: 100vh;
	}

	nav {
		padding: 30px;

		a {
			font-weight: bold;
			color: var(--slate-700);

			&.router-link-exact-active {
				color: var(--orange-500);
			}
		}
	}
</style>