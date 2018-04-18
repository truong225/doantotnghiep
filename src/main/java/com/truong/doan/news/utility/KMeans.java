package com.truong.doan.news.utility;

public class KMeans {
    public void getCenters(){
        /*
        while (go) {
            clusters = new HashMap < double[], TreeSet < Integer >> (step);
            //cluster assignment step
            for (int i = 0; i < vecspace.size(); i++) {
                double[] cent = null;
                double sim = 0;
                for (double[] c: clusters.keySet()) {
                    double csim = cosSim(vecspace.get(i), c);
                    if (csim > sim) {
                        sim = csim;
                        cent = c;
                    }
                }
                clusters.get(cent).add(i);
            }
            //centroid update step
            step.clear();
            for (double[] cent: clusters.keySet()) {
                double[] updatec = new double[cent.length];
                for (int d: clusters.get(cent)) {
                    double[] doc = vecspace.get(d);
                    for (int i = 0; i < updatec.length; i++)
                        updatec[i] += doc[i];
                }
                for (int i = 0; i < updatec.length; i++)
                    updatec[i] /= clusters.get(cent).size();
                step.put(updatec, new TreeSet < Integer > ());
            }
            //check break conditions
            String oldcent = "", newcent = "";
            for (double[] x: clusters.keySet())
                oldcent += Arrays.toString(x);
            for (double[] x: step.keySet())
                newcent += Arrays.toString(x);
            if (oldcent.equals(newcent)) go = false;
            if (++iter >= maxiter) go = false;
        }
        System.out.println(clusters.toString().replaceAll("\\[[\\w@]+=", ""));
        */
    }
}
