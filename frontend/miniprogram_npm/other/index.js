Component({
  data: {
    value: '',
  },

  methods: {
    onChange({ detail }) {
      wx.navigateTo({
        url: '/pages/search-view/search',
    });
    },
  },
});
