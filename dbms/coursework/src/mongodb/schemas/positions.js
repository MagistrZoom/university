var mongoose = require('mongoose');
var table = 'positions';
var schema = mongoose.Schema({
	name: String,
	description: String,
});

module.exports = {
	schema: schema,
	model: mongoose.model(table, schema)
}
