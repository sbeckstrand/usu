##################################################################
# module: cs3430_s22_hw08.py
# description: unit tests CS3430: S22: HW08.
# YOUR NAME
# YOUR A#
# bugs to vladimir kulyukin via canvas
##################################################################

import numpy as np
from scipy.io import wavfile
import math
import matplotlib.pyplot as plt
from rmb import rmb

def nth_partial_sum_of_fourier_series(x, acoeffs, bcoeffs):
    assert len(acoeffs) == len(bcoeffs)+1
    ###    0            1            2         3                n
    ### acoeffs[0], acoeffs[1], acoeffs[2], acoeffs[3], ..., acoeffs[n-1]
    ###             bcoeffs[0], bcoeffs[1], bcoeffs[2], ..., bcoeffs[n-1]
    ### your code

    total = 0;
    for i in range(1, len(bcoeffs) + 1):
        total += (acoeffs[i] * math.cos(i * x)) + (bcoeffs[i - 1] * math.sin(i * x))

    result = (acoeffs[0] / 2) + total

    return result

f = lambda x: x**2
a, b = -math.pi, math.pi
cos_coeffs = []
sin_coeffs = []
for i in range(500):
    fc = lambda x: f(x)*math.cos(i*x)
    ai = rmb.rjl(fc, a, b, 13, 13)/math.pi
    cos_coeffs.append(ai)
    if i > 0:
        fs = lambda x: f(x)*math.sin(i*x)
        bi = rmb.rjl(fs, a, b, 13, 13)/math.pi
        sin_coeffs.append(bi)

# print(cos_coeffs)
# print(sin_coeffs)

def read_wavfile(fpath):
    """
    read a wav file and return frequency, amplitude array
    """
    return wavfile.read(fpath)

### The generic formulas is where period can be 2pi or 2l.
### Hence, half_period is pi or l.
def a_coeff(data, half_period, t, dt, n):
    vf = np.vectorize(lambda t: math.cos((math.pi*n*t)/half_period)*dt)
    return sum(data*vf(t))/half_period

def b_coeff(data, half_period, t, dt, n):
    vf = np.vectorize(lambda t: math.sin((math.pi*n*t)/half_period)*dt)
    return sum(data*vf(t))/half_period

def recover_fourier_coeffs_in_range(fpath, lower_k = 0, upper_k=50):
    """
    - compute a_k and b_k where k is in [lower_k, upper_k].
    - return two arrays: array of a_k's and array of b_k's
    """
    assert lower_k >= 0 and upper_k >= 0
    assert lower_k <= upper_k
    fs, f_of_t = read_wavfile(fpath)
    ### take the amps from channel 0.
    f_of_t = np.array([amp[0] for amp in f_of_t])
    t = np.linspace(-math.pi, math.pi, len(f_of_t))
    dt = t[1] - t[0]
    half_period = math.pi
    
    ### your code
    
    if lower_k == 0:
        assert len(acoeffs) == len(bcoeffs) + 1
    else:
        assert len(acoeffs) == len(bcoeffs)
    return acoeffs, bcoeffs

def plot_recovered_coeffs(coeffs, plot_title, recorded_note, recovered_note, lower_k, upper_k):
    """
    scatter plot recovered coeffs with appropriate title and range.
    """
    fig = plt.figure()
    fig.suptitle(plot_title.format(recorded_note, recovered_note, lower_k, upper_k))
    t = np.array([i for i in range(lower_k, upper_k+1)])
    print(t)
    plt.xlabel('k')
    plt.ylabel('coeff')
    plt.grid()
    plt.scatter(t, coeffs, label='rec coeff', c='b')    
    plt.legend(loc='best')
    plt.show()

    