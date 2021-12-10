var app = new Vue({
	el: '#app',
	data: {
		characters: "",
		words: [],
		indexOfFirst: 0,
		totalFound: 0,
		maxToReturn: 1000,
		minWordLen: 3,
		badRequest: false,
		errorMsg: ""
	},
	mounted: function () {
		document.getElementById("charactersInput").focus();
	},
	computed: {
		isPrevDisabled: function() {
			return this.indexOfFirst == 0;
		},
		isNextDisabled: function() {
			return !((this.indexOfFirst + this.words.length) < this.totalFound);
		}
	},
	methods: {
		getWords: function () {
			// Remove invalid characters from 'characters'
			var scrubbedChars = "";

			for (var i = 0; i < this.characters.length; ++i) {
				var curChar = this.characters.charAt(i).toUpperCase();
				if (curChar >= 'A' && curChar <= 'Z') {
					scrubbedChars += curChar;
				}
			}

			this.characters = scrubbedChars;

			fetch("findwords?characters=" + this.characters +
				"&minNumChars=3&indexOfFirst=" + this.indexOfFirst +
				"&maxToReturn=" + this.maxToReturn)
				.then((response) => {
					if (!response.ok) {
						this.badRequest = true;
						return response.text();
					}
					else {
						this.badRequest = false;
						return response.json();
					}
				})
				.then((data) => {
					if (this.badRequest) {
						this.errorMsg = data;
					}
					else {
						this.words = data.words;
						this.totalFound = data.totalFound;
						this.indexOfFirst = data.indexOfFirst;

						// Handle case of number of results getting smaller
						if (this.indexOfFirst > 0 && this.words.length == 0)
						{
							this.indexOfFirst = 0;
							this.getWords();
						}
					}

					// Right pad with non-breaking spaces each word to be as long as the longest word
					// so that they'll stay aligned when wrapped
					/*

					No longer needed since words are divided into sections by length
					if (this.words && this.words.length > 0) {
						var longestWord = this.words[this.words.length - 1].length;
						for (var i = 0; i < this.words.length; ++i) {
							this.words[i] = this.words[i].padEnd(longestWord, '\xA0');
						}
					}
					*/
				})
		},
		prev: function () {
			this.indexOfFirst -= this.maxToReturn;
			if (this.indexOfFirst < 0) {
				this.indexOfFirst = 0;
			}
			this.getWords();
		},
		next: function () {
			this.indexOfFirst += this.words.length;
			this.getWords();
		},
		clear: function () {
			this.characters = "";
			this.words = [];
			this.indexOfFirst = 0;
			this.totalFound = 0;
			document.getElementById("charactersInput").focus();
		}
	}
});

function letterPressed(letter) {
	letter.style.backgroundColor = '#333333';
}

function letterReleased(letter) {
	letter.style.backgroundColor = '#fffd8d';
}
