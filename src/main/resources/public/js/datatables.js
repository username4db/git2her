$(document).ready(function() {

    var arrayOfArray = [
        [
            "Tiger Nixon",
            "System Architect",
            "Edinburgh",
            "5421",
            "2011/04/25",
            "$3,120"
        ],
        [
            "Garrett Winters",
            "Director",
            "Edinburgh",
            "8422",
            "2011/07/25",
            "$5,300"
        ]
    ];

    var arrayOfObject = [{
        "name": "Tiger Nixon",
        "position": "System Architect",
        "salary": "$3,120",
        "start_date": "2011/04/25",
        "office": "Edinburgh",
        "extn": "5421"
    }, {
        "name": "Garrett Winters",
        "position": "Director",
        "salary": "$5,300",
        "start_date": "2011/07/25",
        "office": "Edinburgh",
        "extn": "8422"
    }];

    function Employee(name, position, salary, office) {
        this.name = name;
        this.position = position;
        this.salary = salary;
        this._office = office;

        this.office = function() {
            return this._office;
        }
    };

    var table = $('#table_id').DataTable({
        paging: true,
        ordering: true,
        info: true,
        order: [
            [2, 'desc']
        ],
        data: [
            new Employee('Tiger Nixon', 'System Architect', '$120', 'Edinburgh'), //
            new Employee('Tiger Nixon', 'System Architect', '$1,120', 'Edinburgh'), //
            new Employee('Tiger Nixon', 'System Architect', '$2,120', 'Edinburgh'), //
            new Employee('Tiger Nixon', 'System Architect', '$3,120', 'Edinburgh')
        ],
        columns: [
            { data: 'name' },
            { data: 'position' },
            { data: 'salary' },
            { data: 'office' },
            { defaultContent: '<button class="delete">delete</button><button class="show">show</button>' }
        ]
    });

    $('#table_id tbody').on('click', 'button.delete', function() {
        var row = table.row($(this).parents('tr'));
        var data = row.data();
        row.remove();
        table.draw();
    });

    $('#table_id tbody').on('click', 'button.show', function() {
        var data = table.row($(this).parents('tr')).data();
        alert(data.name);
    });

});