	const vueApp = Vue.createApp({
		data() {
			return {
				show: true,
				items:[
					{key:'1',value:'A'}
				]
			}
		},
		methods: {
			toggle() {
				this.show = !this.show
			}
		},
		computed: {
			filtered() {
				return this.items.filter((item) => {return true})
			}
		}
	})
