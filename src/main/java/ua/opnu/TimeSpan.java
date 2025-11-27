package ua.opnu;

public class TimeSpan {
    private int hours;
    private int minutes;


    public TimeSpan() {
        this(0, 0);
    }

  
    public TimeSpan(int minutes) {
        this(0, minutes);
    }


    public TimeSpan(int hours, int minutes) {
        if (hours < 0 || minutes < 0) {
            this.hours = 0;
            this.minutes = 0;
            return;
        }
        this.hours = hours + minutes / 60;
        this.minutes = minutes % 60;
    }

    public TimeSpan(TimeSpan ts) {
        this(ts.getHours(), ts.getMinutes());
    }


    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }


    public void add(int hours, int minutes) {
        if (hours < 0 || minutes < 0) return;

        this.hours += hours;
        this.minutes += minutes;

        if (this.minutes >= 60) {
            this.hours += this.minutes / 60;
            this.minutes = this.minutes % 60;
        }
    }


    public void add(int minutes) {
        add(0, minutes);
    }

    public void add(TimeSpan span) {
        if (span == null) return;
        add(span.getHours(), span.getMinutes());
    }



    public void subtract(int hours, int minutes) {
        if (hours < 0 || minutes < 0) return;

        int totalThis = getTotalMinutes();
        int totalOther = hours * 60 + minutes;

        if (totalOther > totalThis) return;

        int diff = totalThis - totalOther;

        this.hours = diff / 60;
        this.minutes = diff % 60;
    }

    public void subtract(int minutes) {
        subtract(0, minutes);
    }

    public void subtract(TimeSpan span) {
        if (span == null) return;
        subtract(span.getHours(), span.getMinutes());
    }


    public double getTotalHours() {
        return hours + minutes / 60.0;
    }

    public int getTotalMinutes() {
        return hours * 60 + minutes;
    }

    public void scale(int factor) {
        if (factor <= 0) return;

        int total = getTotalMinutes() * factor;
        hours = total / 60;
        minutes = total % 60;
    }
}
