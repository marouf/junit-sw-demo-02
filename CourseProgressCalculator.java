public class CourseProgressCalculator {
    public double calculateProgressPercentage(int completedLessons, int totalLessons) {
        if (totalLessons <= 0) {
            throw new IllegalArgumentException("Total lessons must be greater than zero");
        }
        if (completedLessons < 0 || completedLessons > totalLessons) {
            throw new IllegalArgumentException("Completed lessons must be between 0 and total lessons");
        }
        return ((double) completedLessons / totalLessons) * 100;
    }
}
